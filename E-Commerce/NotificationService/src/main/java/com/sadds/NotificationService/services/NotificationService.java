package com.sadds.NotificationService.services;

import com.sadds.NotificationService.entity.Notification;
import com.sadds.NotificationService.entity.NotificationType;
import com.sadds.NotificationService.entity.OrderConfirmation;
import com.sadds.NotificationService.entity.PaymentConfirmation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void saveOrderConfirmation(OrderConfirmation orderConfirmation) {
        var notification = new Notification(
                NotificationType.ORDER_CONFIRMATION,
                LocalDateTime.now()
                );
        notificationRepository.save(notification);
    }

    public void savePaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        var notification = new Notification(
                NotificationType.PAYMENT_CONFIRMATION,
                LocalDateTime.now()
        );
        notificationRepository.save(notification);
    }
}
