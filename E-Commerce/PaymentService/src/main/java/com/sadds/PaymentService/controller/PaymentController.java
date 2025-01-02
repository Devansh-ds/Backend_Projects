package com.sadds.PaymentService.controller;

import com.sadds.PaymentService.dto.PaymentRequest;
import com.sadds.PaymentService.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Integer> makePayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.makePayment(paymentRequest));
    }

    @GetMapping
    public String greet() {
        return "Hello World";
    }


}
