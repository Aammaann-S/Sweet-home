package org.ncu.bookingService.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(nullable = true)
	private Date fromDate;
	
	@Column(nullable = true)
	private Date toDate;
	
	@Column(nullable = true)
	private String aadharNumber;
	
	private int numOfRooms;
	
	private String roomNumbers;
	
	@Column(nullable = false)
	private int roomPrice;
	
	//@Column(columnDefinition = "0")
	@Column(nullable = true)
	private int transactionId;
	
	@Column(nullable = true)
	private Date bookedOn;

}
	
		
