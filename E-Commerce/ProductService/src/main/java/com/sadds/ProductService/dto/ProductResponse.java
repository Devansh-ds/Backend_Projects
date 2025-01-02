package com.sadds.ProductService.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Double availableQuantity,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
