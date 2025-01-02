package com.sadds.OrderService.service;

import com.sadds.OrderService.dto.OrderRequest;
import com.sadds.OrderService.dto.OrderResponse;
import com.sadds.OrderService.entity.Order;
import com.sadds.OrderService.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private final OrderLineMapper orderLineMapper;

    public OrderMapper(OrderLineMapper orderLineMapper) {
        this.orderLineMapper = orderLineMapper;
    }

    public Order toOrder(OrderRequest orderRequest) {
        return new Order(
            orderRequest.reference(),
                null,
                orderRequest.customerId(),
                orderRequest.paymentMethod(),
                LocalDateTime.now(),
                null
        );
    }

    public List<OrderResponse> toOrderResponse(List<Order> orders) {
        return orders.stream().map(order -> {
            var orderLineResponse = orderLineMapper.toOrderLineResponse(order.getOrderLines());
            return new OrderResponse(
                    order.getTotalAmount(),
                    orderLineResponse,
                    order.getReference(),
                    order.getPaymentMethod()
            );
        }).collect(Collectors.toList());
    }

    public OrderResponse toOrderResponse(Order order) {
        var orderLineResponse = orderLineMapper.toOrderLineResponse(order.getOrderLines());
        return new OrderResponse(
                order.getTotalAmount(),
                orderLineResponse,
                order.getReference(),
                order.getPaymentMethod()
        );
    }
}














