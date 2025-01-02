package com.sadds.OrderService.service;

import com.sadds.OrderService.dto.OrderLineResponse;
import com.sadds.OrderService.dto.ProductPurchaseRequest;
import com.sadds.OrderService.entity.Order;
import com.sadds.OrderService.entity.OrderLines;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineMapper {

    public List<OrderLineResponse> toOrderLineResponse(List<OrderLines> orderLines) {
        return orderLines.stream().map(orderLine -> {
            return new OrderLineResponse(
                    orderLine.getProductId(),
                    orderLine.getQuantity()
            );
        }).collect(Collectors.toList());
    }

    public List<OrderLines> toOrderLine(Order order, List<ProductPurchaseRequest> products) {
        return products.stream().map(product -> {
            return new OrderLines(
                    order,
                    product.productId(),
                    product.quantity()
            );
        }).collect(Collectors.toList());
    }
}
