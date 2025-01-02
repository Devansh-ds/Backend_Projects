package com.sadds.PaymentService.service;

import com.sadds.PaymentService.dto.PaymentRequest;
import com.sadds.PaymentService.kafka.PaymentConfirmation;
import com.sadds.PaymentService.kafka.PaymentProducer;
import com.sadds.PaymentService.repo.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentProducer paymentProducer;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentMapper paymentMapper,
                          PaymentProducer paymentProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.paymentProducer = paymentProducer;
    }

    public Integer makePayment(PaymentRequest paymentRequest) {
        // todo make payment

        var payment = paymentMapper.toPayment(paymentRequest);
        var savedPayment = paymentRepository.save(payment);

        PaymentConfirmation paymentConfirmation = paymentMapper.toPaymentConfirmation(savedPayment, paymentRequest.customerEmail());
        paymentProducer.sendPaymentConfirmation(paymentConfirmation);

        return savedPayment.getId();
    }
}















