package org.ncu.bookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

	private BookingInfoDto booking;
	private TransactionDetailsDto transaction;
	
}
