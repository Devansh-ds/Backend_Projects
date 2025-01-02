package com.sadds.PaymentService.dto;

import com.sadds.PaymentService.entity.PaymentMethod;

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
