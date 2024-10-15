package com.devansh.cart.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private Long productId;
	private String productName;
	private String productBrand;
	private int quantity;
	private BigDecimal price;
}
