package com.devansh.cart.service.cart;

import java.math.BigDecimal;

import com.devansh.cart.model.Cart;
import com.devansh.cart.model.User;

public interface ICartService {

	Cart getCart(Long id);
	void clearCart(Long id);
	BigDecimal getTotalPrice(Long id);
	Cart initializeNewCart(User user);
	Cart getCartByUserId(Long userId);
}
