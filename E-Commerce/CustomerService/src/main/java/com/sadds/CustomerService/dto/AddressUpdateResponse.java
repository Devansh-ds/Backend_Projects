package com.sadds.CustomerService.dto;

public record AddressUpdateResponse(
        String street,
        String city,
        String state,
        String zipcode,
        String country
) {
}
