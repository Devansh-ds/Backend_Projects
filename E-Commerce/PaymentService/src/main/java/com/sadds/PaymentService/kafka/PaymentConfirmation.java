package com.sadds.PaymentService.kafka;

import com.sadds.PaymentService.entity.Payment;
import com.sadds.PaymentService.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerEmail
) {
}
