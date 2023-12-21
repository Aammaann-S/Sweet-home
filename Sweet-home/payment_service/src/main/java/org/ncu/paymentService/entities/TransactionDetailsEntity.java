package org.ncu.paymentService.entities;

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
@Table(name="transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(nullable= false)
	private String paymentMode; 
	
	@Column(nullable= false)
	private int bookingId;
	
	@Column(nullable= true)
	private String upiId; 
	
	@Column(nullable= true)
	private String cardNumber;
	

}
