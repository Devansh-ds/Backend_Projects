package com.sadds.NotificationService.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_table")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @CreatedDate
    private LocalDateTime notificationTime;
    @Transient
    private OrderConfirmation orderConfirmation;
    @Transient
    private PaymentConfirmation paymentConfirmation;

    public Notification() {}

    public Notification(NotificationType notificationType, LocalDateTime notificationTime) {
        this.notificationType = notificationType;
        this.notificationTime = notificationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }


}
