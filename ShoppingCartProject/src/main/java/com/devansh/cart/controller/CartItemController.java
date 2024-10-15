package com.devansh.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devansh.cart.exception.ResourceNotFoundException;
import com.devansh.cart.model.Cart;
import com.devansh.cart.model.User;
import com.devansh.cart.response.ApiResponse;
import com.devansh.cart.service.User.IUserService;
import com.devansh.cart.service.cart.ICartItemService;
import com.devansh.cart.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItem")
public class CartItemController {

	private final ICartItemService cartItemService;
	private final ICartService cartService;
	private final IUserService userService;
	
	@PostMapping("/item/add")
	public ResponseEntity<ApiResponse> addItemToCart(
			@RequestParam Long productId,
			@RequestParam Integer quantity) {
		try {
			User user = userService.getUserById(4L);
			Cart cart = cartService.initializeNewCart(user);
			
			cartItemService.addItemToCart(cart.getId(), productId, quantity);
			return ResponseEntity.ok(new ApiResponse("Add item success", null));
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/cart/{cartId}/item/{productId}/remove")
	public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, 
														  @PathVariable Long productId) {
		try {
			cartItemService.removeItemFromCart(cartId, productId);
			return ResponseEntity.ok(new ApiResponse("Remove item success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PutMapping("/cart/{cartId}/item/{productId}/update")
	public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
														  @PathVariable Long productId,
														  @RequestParam Integer quantity) {
		try {
			cartItemService.updateItemQuantity(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponse("update item success", null));
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(new ApiResponse(e.getMessage(), null));
		}
	}
		
	
}

























