package com.sadds.NotificationService.email;

import java.math.BigDecimal;

public record EmailTemplate(
        String from,
        String to,
        String subject,
        String body,
        String attachments,
        String customerName,
        String fullAddress,
        BigDecimal totalAmount,
        String productName,
        BigDecimal productPrice,
        Double quantityPurchased
) {
}
