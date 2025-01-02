package com.sadds.CustomerService.dto;


public record CustomerUpdateRequest(
        String firstname,
        String lastname,
        String email,
        String phone
) {
}
