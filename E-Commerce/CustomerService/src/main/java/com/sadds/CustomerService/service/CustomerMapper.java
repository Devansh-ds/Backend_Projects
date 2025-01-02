package com.sadds.CustomerService.service;

import com.sadds.CustomerService.entity.Address;
import com.sadds.CustomerService.entity.Customer;
import com.sadds.CustomerService.dto.CustomerRequest;
import com.sadds.CustomerService.dto.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress().getStreet(),
                customer.getAddress().getCity(),
                customer.getAddress().getState(),
                customer.getAddress().getZipcode(),
                customer.getAddress().getCountry()
        );
    }

    public Customer toCustomer(@Valid CustomerRequest request) {
        return Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .phone(request.phone())
                .address(Address.builder()
                        .street(request.street())
                        .city(request.city())
                        .state(request.state())
                        .zipcode(request.zipcode())
                        .country(request.country())
                        .build())
                .build();
    }
}
