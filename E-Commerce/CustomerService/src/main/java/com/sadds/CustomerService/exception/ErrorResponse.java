package com.sadds.CustomerService.exception;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<String, String> errors
) {
}
