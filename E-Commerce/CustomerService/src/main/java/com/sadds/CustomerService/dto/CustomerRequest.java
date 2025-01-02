package com.sadds.CustomerService.dto;

import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerRequest(
        @NotNull(message = "firstname cannot be null")
        @NotBlank(message = "blank firstname is not allowed")
        @NotEmpty(message = "write something")
        String firstname,

        @NotNull(message = "lastname cannot be null")
        @NotBlank(message = "blank lastname is not allowed")
        @NotEmpty(message = "write something")
        String lastname,

        @Email
        String email,

        @Pattern(regexp = "[0-9]{10}")
        String phone,

        String street,
        String city,
        String state,
        String zipcode,

        @NotNull(message = "country cannot be null")
        @NotEmpty(message = "country name cannot be empty")
        @NotBlank(message = "country name cannot be blank")
        String country
) {
}













