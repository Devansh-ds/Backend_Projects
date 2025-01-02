package com.sadds.OrderService.service;

import com.sadds.OrderService.dto.OrderLineResponse;
import com.sadds.OrderService.entity.OrderLines;
import com.sadds.OrderService.repo.OrderLineRepository;
import com.sadds.OrderService.repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public OrderLineService(OrderLineRepository orderLineRepository,
                            OrderLineMapper orderLineMapper) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineMapper = orderLineMapper;
    }

    public void saveOrderLine(OrderLines orderLines) {
        orderLineRepository.save(orderLines);
    }

    public List<OrderLineResponse> getAllOrderLine(Integer orderId) {
        List<OrderLines> orderLines = orderLineRepository.findByOrderId(orderId);
        return orderLineMapper.toOrderLineResponse(orderLines);
    }
}
