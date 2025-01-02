package com.sadds.OrderService.dto;

import com.sadds.OrderService.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record OrderRequest(
        @NotNull(message = "customer id cannot be null")
        Integer customerId,
        List<ProductPurchaseRequest> products,
        String reference,
        PaymentMethod paymentMethod
) {
}
