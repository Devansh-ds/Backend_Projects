package com.sadds.CustomerService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record AddressUpdateRequest(
        String street,
        String city,
        String state,
        String zipcode,
        @NotNull(message = "country cannot be null")
        @NotBlank(message = "country cannot be blank")
        @NotEmpty(message = "country name cannot be empty")
        String country
) {
}
