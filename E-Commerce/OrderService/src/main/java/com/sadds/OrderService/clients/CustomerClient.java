package com.sadds.OrderService.clients;

import com.sadds.OrderService.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "${application.config.customer-service-url}")
public interface CustomerClient {

    @GetMapping("customers/{customer-id}")
    public Optional<CustomerResponse> getCustomerById(@PathVariable("customer-id") Integer customerId);

}
