package com.sadds.OrderService.dto;

public record OrderLineResponse(
        Integer productId,
        Double quantity
) {
}
