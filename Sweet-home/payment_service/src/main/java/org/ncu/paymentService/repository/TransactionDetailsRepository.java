package org.ncu.paymentService.repository;

import org.ncu.paymentService.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetailsEntity, Integer>{
	
	TransactionDetailsEntity findByBookingId(int bookingId);

}
