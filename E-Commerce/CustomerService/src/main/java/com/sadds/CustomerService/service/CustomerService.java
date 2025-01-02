package com.sadds.CustomerService.service;

import com.sadds.CustomerService.dto.*;
import com.sadds.CustomerService.entity.*;
import com.sadds.CustomerService.repository.AddressRepository;
import com.sadds.CustomerService.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new EntityNotFoundException("Customer not found with id: " + customerId));
        return customerMapper.toCustomerResponse(customer);
    }

    public Integer createCustomers(@Valid CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerRepository.save(customer);
        return customer.getId();
    }

    public void updateCustomer(Integer customerId, @Valid CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new EntityNotFoundException("No update, Customer not found with id: " + customerId));
        if (request.firstname() != null) {
            customer.setFirstname(request.firstname());
        }
        if (request.lastname() != null) {
            customer.setLastname(request.lastname());
        }
        if (request.phone() != null) {
            customer.setPhone(request.phone());
        }
        if (request.email() != null) {
            customer.setEmail(request.email());
        }
        customerRepository.save(customer);
    }

    public Integer deleteById(Integer customerId) {
        customerRepository.findById(customerId).orElseThrow(() ->
                new EntityNotFoundException("Customer not found with id: " + customerId));
        customerRepository.deleteById(customerId);
        return customerId;
    }

    public AddressUpdateResponse addressUpdate(Integer customerId, @Valid AddressUpdateRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new EntityNotFoundException("Customer not found with id: " + customerId));
        Address address = customer.getAddress();

        Address updatedAddress = addressMapper.toAddress(request);
        updatedAddress.setId(address.getId());
        addressRepository.save(updatedAddress);
        customer.setAddress(updatedAddress);

        customerRepository.save(customer);
        return addressMapper.toAddressUpdateResponse(updatedAddress);
    }
}















