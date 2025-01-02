package com.sadds.OrderService.repo;

import com.sadds.OrderService.entity.OrderLines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLines, Integer> {
    List<OrderLines> findByOrderId(Integer orderId);

    List<OrderLines> getByOrderId(Integer id);
}
