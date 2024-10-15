package com.devansh.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devansh.cart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserId(Long userId);

}
