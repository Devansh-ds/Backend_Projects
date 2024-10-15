package com.devansh.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devansh.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByUserId(Long userId);
	void deleteById(Long id);

}
