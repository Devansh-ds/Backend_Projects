package com.sadds.OrderService.kafka;

import com.sadds.OrderService.dto.CustomerResponse;
import com.sadds.OrderService.dto.ProductPurchaseResponse;
import com.sadds.OrderService.entity.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        Double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<ProductPurchaseResponse> productPurchaseResponses
) {
}
