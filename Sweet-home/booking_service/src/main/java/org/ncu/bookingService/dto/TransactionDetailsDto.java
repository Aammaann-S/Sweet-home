package org.ncu.bookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsDto {

	private int transactionId;
	private String paymentMode; 	
	private int bookingId; 	
	private String upiId; 
	private String cardNumber;
	
}
