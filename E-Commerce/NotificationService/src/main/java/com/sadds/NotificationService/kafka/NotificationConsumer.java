package com.sadds.NotificationService.kafka;

import com.sadds.NotificationService.email.EmailService;
import com.sadds.NotificationService.entity.OrderConfirmation;
import com.sadds.NotificationService.entity.PaymentConfirmation;
import com.sadds.NotificationService.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final EmailService emailService;

    public NotificationConsumer(NotificationService notificationService,
                                EmailService emailService) {
        this.notificationService = notificationService;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "ec-order-topic", groupId = "orderGroup")
    public void getOrderConfirmation(OrderConfirmation orderConfirmation) {
        System.out.println("-----------------Got OrderConfirmation ----------------");
        notificationService.saveOrderConfirmation(orderConfirmation);

        // send email for order confirmation
        emailService.sendOrderConfirmEmail(orderConfirmation);
    }

    @KafkaListener(topics = "ec-payment-topic", groupId = "paymentGroup")
    public void getPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        System.out.println("-----------------Got PaymentConfirmation ----------------");
        notificationService.savePaymentConfirmation(paymentConfirmation);

        // send email for payment confirmation
        emailService.sendPaymentConfirmEmail(paymentConfirmation);
    }

}
