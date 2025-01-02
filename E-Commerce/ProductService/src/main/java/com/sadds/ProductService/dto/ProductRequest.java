package com.sadds.ProductService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record ProductRequest(
        @NotNull(message = "name of the product cannot be null")
        @NotEmpty(message = "name of the product cannnot be empty")
        @NotBlank(message = "no blank product name")
        String name,
        @NotNull(message = "description cannot be null")
        String description,
        @NotNull(message = "price must be mentioned for the product")
        @Positive(message = "price cannot be negative")
        BigDecimal price,
        @Positive(message = "quantity cannot be negative")
        Double availableQuantity,
        @Positive(message = "product must belong to some category")
        @NotNull
        Integer categoryId
) {

}
