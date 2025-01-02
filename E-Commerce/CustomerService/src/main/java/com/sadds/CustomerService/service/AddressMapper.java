package com.sadds.CustomerService.service;

import com.sadds.CustomerService.entity.Address;
import com.sadds.CustomerService.dto.AddressUpdateRequest;
import com.sadds.CustomerService.dto.AddressUpdateResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public Address toAddress(@Valid AddressUpdateRequest request) {
        return Address.builder()
                .street(request.street())
                .city(request.city())
                .state(request.state())
                .zipcode(request.zipcode())
                .country(request.country())
                .build();
    }

    public AddressUpdateResponse toAddressUpdateResponse(Address updatedAddress) {
        return new AddressUpdateResponse(
                updatedAddress.getStreet(),
                updatedAddress.getCity(),
                updatedAddress.getState(),
                updatedAddress.getZipcode(),
                updatedAddress.getCountry()
        );
    }
}













