package com.sadds.OrderService.dto;

import com.sadds.OrderService.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal totalAmount,
        Integer customerId,
        String orderReference,
        Integer orderId,
        PaymentMethod paymentMethod,
        String customerEmail
) {
}
