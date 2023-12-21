package org.ncu.bookingService.exception;

public class InvalidPaymentModeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidPaymentModeException(String message) {
		super(message);
//		System.out.println("check1");
	}

}
