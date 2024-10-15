package com.devansh.cart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devansh.cart.dto.OrderDto;
import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.Order;
import com.devansh.cart.response.ApiResponse;
import com.devansh.cart.service.order.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
public class OrderController {

	private final IOrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
		try {
			Order order = orderService.placeOrder(userId);
			OrderDto orderDto = orderService.convertToDto(order);
			return ResponseEntity.ok(new ApiResponse("Order success", orderDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/{orderId}/order")
	public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
		try {
			OrderDto orderDto = orderService.getOrder(orderId);
			return ResponseEntity.ok(new ApiResponse("order fetched successfully", orderDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/{userId}/orders")
	public ResponseEntity<ApiResponse> getUserOrder(@PathVariable Long userId) {
		try {
			List<OrderDto> orderDto = orderService.getUserOrders(userId);
			return ResponseEntity.ok(new ApiResponse("order fetched successfully", orderDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	
	
}
















