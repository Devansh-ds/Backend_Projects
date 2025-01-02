package com.sadds.NotificationService.entity;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantityPurchased
) {
}
