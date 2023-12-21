package org.ncu.paymentService.services;

import java.util.Map;

import org.ncu.paymentService.entities.TransactionDetailsEntity;

public interface TransactionDetailsService {
	
	public int addTransaction(TransactionDetailsEntity transactionDetails);
	public TransactionDetailsEntity getTransactionById(int transactionId);
	public Map<Integer,TransactionDetailsEntity> getAllTransactions();
	public TransactionDetailsEntity getTransactionByBookingId(int bookingId);

}
