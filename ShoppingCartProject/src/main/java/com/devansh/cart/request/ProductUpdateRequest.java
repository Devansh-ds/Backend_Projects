package com.devansh.cart.request;

import java.math.BigDecimal;

import com.devansh.cart.model.Category;

import lombok.Data;

@Data
public class ProductUpdateRequest {
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;
	private Category category;
}
