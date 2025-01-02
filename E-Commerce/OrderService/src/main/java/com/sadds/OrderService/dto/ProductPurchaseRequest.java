package com.sadds.OrderService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record ProductPurchaseRequest(
        Integer productId,
        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity must be positive")
        Double quantity
) {
}
