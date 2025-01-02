package com.sadds.NotificationService.entity;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        Double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<ProductPurchaseResponse> productPurchaseResponses
) {
}
