package org.ncu.bookingService.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingInfoDto {
	
	private int bookingId;
	private Date fromDate; 
	private Date toDate; 
	private String aadharNumber; 
	private int numOfRooms; 
	private String roomNumbers;
	private int roomPrice; 
	private int transactionId; 
	private Date bookedOn;


}
