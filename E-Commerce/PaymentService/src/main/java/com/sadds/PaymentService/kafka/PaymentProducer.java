package com.sadds.PaymentService.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, PaymentConfirmation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        Message<PaymentConfirmation> message = MessageBuilder
                .withPayload(paymentConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "ec-payment-topic")
                .build();
        kafkaTemplate.send(message);

        System.out.println("--------------------Sent payment Confirmation----------------------");
    }

}
