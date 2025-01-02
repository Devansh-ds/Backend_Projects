package com.sadds.OrderService.repo;

import com.sadds.OrderService.dto.OrderResponse;
import com.sadds.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerId(Integer customerId);
}
