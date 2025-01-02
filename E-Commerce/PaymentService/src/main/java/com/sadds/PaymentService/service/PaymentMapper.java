package com.sadds.PaymentService.service;

import com.sadds.PaymentService.dto.PaymentRequest;
import com.sadds.PaymentService.entity.Payment;
import com.sadds.PaymentService.kafka.PaymentConfirmation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return new Payment(
                paymentRequest.totalAmount(),
                paymentRequest.customerId(),
                paymentRequest.orderReference(),
                paymentRequest.paymentMethod(),
                LocalDateTime.now()
        );
    }

    public PaymentConfirmation toPaymentConfirmation(Payment savedPayment, String customerEmail) {
        return new PaymentConfirmation(
                savedPayment.getOrderReference(),
                savedPayment.getTotalAmount(),
                savedPayment.getPaymentMethod(),
                customerEmail
        );
    }
}
