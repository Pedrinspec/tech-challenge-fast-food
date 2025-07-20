package com.fiap.fast_food_tc.domain.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, int status, String message) {
}
