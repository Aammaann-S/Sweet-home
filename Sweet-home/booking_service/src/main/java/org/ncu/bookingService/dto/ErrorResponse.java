package org.ncu.bookingService.dto;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
	private String message;
    private Map<String, Object> errorDetails;

    public ErrorResponse(String message, Map<String, Object> errorDetails) {
        this.message = message;
        this.errorDetails = errorDetails;
    }
}
