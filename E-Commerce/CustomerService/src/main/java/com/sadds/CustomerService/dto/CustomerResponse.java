package com.sadds.CustomerService.dto;

public record CustomerResponse(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String street,
        String city,
        String state,
        String zipcode,
        String country
) {
}
