package com.sadds.OrderService.dto;

import com.sadds.OrderService.entity.PaymentMethod;

import java.util.List;

public record OrderResponse(
        Double totalAmount,
        List<OrderLineResponse> products,
        String reference,
        PaymentMethod paymentMethod
) {
}
