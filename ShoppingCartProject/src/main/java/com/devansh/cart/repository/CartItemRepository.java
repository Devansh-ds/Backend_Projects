package com.devansh.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devansh.cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	void deleteAllByCartId(Long id);

}
