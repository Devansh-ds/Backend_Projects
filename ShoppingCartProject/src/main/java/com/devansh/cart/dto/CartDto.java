package com.devansh.cart.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;

@Data
public class CartDto {

	private Long cartId;
	private Set<CartItemDto> items;
	private BigDecimal totalAmount;
}
