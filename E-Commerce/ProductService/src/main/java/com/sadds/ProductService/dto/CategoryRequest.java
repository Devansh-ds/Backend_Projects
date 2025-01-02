package com.sadds.ProductService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record CategoryRequest(
        @NotNull(message = "category name cannot be null")
        @NotBlank(message = "category name cannot be blank")
        @NotEmpty(message = "category name cannot be empty")
        String name,
        @NotNull(message = "Description cannot be null")
        String descriptions
) {
}
