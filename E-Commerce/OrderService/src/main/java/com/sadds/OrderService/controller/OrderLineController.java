package com.sadds.OrderService.controller;

import com.sadds.OrderService.dto.OrderLineResponse;
import com.sadds.OrderService.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-line")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLine(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderLineService.getAllOrderLine(orderId));
    }

}
