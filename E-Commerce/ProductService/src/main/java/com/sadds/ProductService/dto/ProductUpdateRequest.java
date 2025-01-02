package com.sadds.ProductService.dto;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;


public record ProductUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        Double availableQuantity,
        Integer categoryId
) {
}
