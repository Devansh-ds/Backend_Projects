package com.sadds.ProductService.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
