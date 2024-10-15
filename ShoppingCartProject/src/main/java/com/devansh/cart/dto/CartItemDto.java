package com.devansh.cart.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemDto {

	private Long id;
	private Integer quantity;
	private BigDecimal unitPrice;
	private ProductDto product;
}
