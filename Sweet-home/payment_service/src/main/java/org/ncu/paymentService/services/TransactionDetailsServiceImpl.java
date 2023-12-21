package org.ncu.paymentService.services;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


import org.ncu.paymentService.entities.TransactionDetailsEntity;
import org.ncu.paymentService.repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService{
	
	@Autowired
	TransactionDetailsRepository transactionDetailsRepository;

	@Override
	public int addTransaction(TransactionDetailsEntity transactionDetails) {
		// TODO Auto-generated method stub
		TransactionDetailsEntity transaction = transactionDetailsRepository.save(transactionDetails);
		return transaction.getTransactionId();
	}

	@Override
	public TransactionDetailsEntity getTransactionById(int transactionId) {
		// TODO Auto-generated method stub
		TransactionDetailsEntity transaction = transactionDetailsRepository.findById(transactionId).get();
		return transaction;
	}

	@Override
	public Map<Integer,TransactionDetailsEntity> getAllTransactions() {
		// TODO Auto-generated method stub
		
		List<TransactionDetailsEntity> list = transactionDetailsRepository.findAll();
		Map<Integer,TransactionDetailsEntity> map = new HashMap<Integer, TransactionDetailsEntity>();
		for(TransactionDetailsEntity tr: list) {
			System.out.println(tr);
			map.put(tr.getTransactionId(), tr);
		}
		return map;
	}
	
	@Override
	public TransactionDetailsEntity getTransactionByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		TransactionDetailsEntity transaction = transactionDetailsRepository.findByBookingId(bookingId);
		return transaction;
	}
	
	

}
