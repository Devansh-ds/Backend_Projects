package com.devansh.cart.service.order;

import java.util.List;

import com.devansh.cart.dto.OrderDto;
import com.devansh.cart.model.Order;

public interface IOrderService {

	Order placeOrder(Long userId);
	OrderDto getOrder(Long userId);
	List<OrderDto> getUserOrders(Long userId);
	OrderDto convertToDto(Order order);
	
}
