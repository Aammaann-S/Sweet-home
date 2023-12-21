package org.ncu.bookingService.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ncu.bookingService.dto.ErrorResponse;
import org.ncu.bookingService.exception.InvalidBookingIdException;
import org.ncu.bookingService.exception.InvalidPaymentModeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private String INVALID_MODE = "Invalid Mode of Payment";
	private String INVALID_BOOKING = "Invalid Booking Id";
	
	@ExceptionHandler(InvalidPaymentModeException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidModeException(InvalidPaymentModeException e){
//		System.out.println("check2");

		Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.add(e.getLocalizedMessage());
		errorDetails.put("message", e.getLocalizedMessage());
	    errorDetails.put("statusCode", HttpStatus.BAD_REQUEST.value());
//        ErrorResponse response = new ErrorResponse(INVALID_MODE, errorDetails);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidBookingIdException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidBookingException(InvalidBookingIdException e){

        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.add(e.getLocalizedMessage());
        errorDetails.put("message", e.getLocalizedMessage());
	    errorDetails.put("statusCode", HttpStatus.BAD_REQUEST.value());
        ErrorResponse response = new ErrorResponse(INVALID_BOOKING, errorDetails);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
