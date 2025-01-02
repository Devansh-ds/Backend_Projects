package com.sadds.OrderService.service;

import com.sadds.OrderService.clients.CustomerClient;
import com.sadds.OrderService.clients.PaymentClient;
import com.sadds.OrderService.clients.ProductClient;
import com.sadds.OrderService.dto.*;
import com.sadds.OrderService.entity.OrderLines;
import com.sadds.OrderService.kafka.OrderConfirmation;
import com.sadds.OrderService.kafka.OrderProducer;
import com.sadds.OrderService.repo.OrderLineRepository;
import com.sadds.OrderService.repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final OrderLineMapper orderLineMapper;
    private final OrderLineRepository orderLineRepository;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository,
                        CustomerClient customerClient,
                        ProductClient productClient,
                        OrderMapper orderMapper,
                        OrderLineService orderLineService,
                        OrderProducer orderProducer,
                        OrderLineMapper orderLineMapper, OrderLineRepository orderLineRepository,
                        PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
        this.orderLineMapper = orderLineMapper;
        this.orderLineRepository = orderLineRepository;
        this.paymentClient = paymentClient;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {

        // check if customer exist or not
        var customer = customerClient.getCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + orderRequest.customerId()));

        // purchase all the products
        var productPurchaseResponse = productClient.purchaseProduct(orderRequest.products())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + orderRequest.products()));

        // persist the order
        var order = orderMapper.toOrder(orderRequest);
        order.setCreatedDate(LocalDateTime.now());
        double totalAmount = 0.0;
        for (ProductPurchaseResponse response: productPurchaseResponse) {
            totalAmount += response.quantityPurchased() * response.price().doubleValue();
        }

        order.setTotalAmount(totalAmount);
        var savedOrder = orderRepository.save(order);

        // persist the order-lines
        for (var product: productPurchaseResponse) {
            orderLineService.saveOrderLine(new OrderLines(
                    savedOrder,
                    product.productId(),
                    product.quantityPurchased()
            ));
        }

        // start the payment process
        PaymentRequest paymentRequest = new PaymentRequest(
                BigDecimal.valueOf(totalAmount),
                customer.id(),
                savedOrder.getReference(),
                savedOrder.getId(),
                savedOrder.getPaymentMethod(),
                customer.email()
        );
        paymentClient.makePayment(paymentRequest);

        // send the order confirm notification
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        savedOrder.getReference(),
                        totalAmount,
                        savedOrder.getPaymentMethod(),
                        customer,
                        productPurchaseResponse
                )
        );
        List<OrderLines> orderLines = orderLineRepository.getByOrderId(savedOrder.getId());
        var orderLineResponse = orderLineMapper.toOrderLineResponse(orderLines);

        return new OrderResponse(
                totalAmount,
                orderLineResponse,
                savedOrder.getReference(),
                savedOrder.getPaymentMethod()
        );
    }

    public List<OrderResponse> findByCustomerId(Integer customerId) {
        var orders = orderRepository.findByCustomerId(customerId);
        if (orders == null) {
            throw new EntityNotFoundException("Customer not found with id " + customerId);
        }

        return orderMapper.toOrderResponse(orders);
    }

    public OrderResponse getOrderById(Integer orderId) {
        var order = orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Order not found with id " + orderId));
        var orderLineResponse = orderLineMapper.toOrderLineResponse(order.getOrderLines());
        return new OrderResponse(
                order.getTotalAmount(),
                orderLineResponse,
                order.getReference(),
                order.getPaymentMethod()
        );
    }

    public OrderResponse updateOrder(Integer orderId, OrderRequest orderRequest) {
        var order = orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Order not found with id " + orderId));
        if (orderRequest.reference() != null) {
            order.setReference(orderRequest.reference());
        }
        if (orderRequest.products() != null) {
            var products = orderRequest.products();
            var orderLineResponse = orderLineMapper.toOrderLine(order, products);
            order.setOrderLines(orderLineResponse);
        }

        if (orderRequest.paymentMethod() != null) {
            order.setPaymentMethod(orderRequest.paymentMethod());
        }

        return orderMapper.toOrderResponse(order);

    }
}










