package com.sadds.OrderService.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
