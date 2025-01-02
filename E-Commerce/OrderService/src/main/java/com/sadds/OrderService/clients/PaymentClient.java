package com.sadds.OrderService.clients;

import com.sadds.OrderService.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "payment-service", url = "${application.config.payment-service-url}")
public interface PaymentClient {

    @PostMapping("/payment")
    public Optional<Integer> makePayment(@RequestBody PaymentRequest paymentRequest);

}
