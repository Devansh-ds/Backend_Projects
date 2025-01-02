package com.sadds.CustomerService.controller;

import com.sadds.CustomerService.dto.*;
import com.sadds.CustomerService.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok("customer created with id: " + customerService.createCustomers(request));
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customer-id") Integer customerId,
                                                 @RequestBody @Valid CustomerUpdateRequest request) {
        customerService.updateCustomer(customerId, request);
        return ResponseEntity.ok("customer updated");
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customer-id") Integer customerId) {
        return ResponseEntity.ok("customer deleted with id: " +
                String.valueOf(customerService.deleteById(customerId)));
    }

    @PutMapping("/{customer-id}/address")
    public ResponseEntity<AddressUpdateResponse> updateCustomerAddress(@PathVariable("customer-id") Integer customerId,
                                                                       @RequestBody @Valid AddressUpdateRequest request) {
        return ResponseEntity.ok(customerService.addressUpdate(customerId, request));
    }

}















