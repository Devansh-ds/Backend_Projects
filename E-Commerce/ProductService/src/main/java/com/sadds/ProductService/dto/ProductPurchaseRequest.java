package com.sadds.ProductService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record ProductPurchaseRequest(
        @NotNull(message = "id cant be null")
        Integer productId,
        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity must be positive")
        Double quantity
) {
}
