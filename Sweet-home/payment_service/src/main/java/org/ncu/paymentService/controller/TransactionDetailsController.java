package org.ncu.paymentService.controller;

import java.util.ArrayList;
import java.util.List;

import org.ncu.paymentService.entities.TransactionDetailsEntity;
import org.ncu.paymentService.services.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class TransactionDetailsController {
	
	@Autowired
	TransactionDetailsService transactionDetailsService;
	
	
	@PostMapping(path = "/transaction")
	public ResponseEntity<Integer> addTransactionDetails(@RequestBody TransactionDetailsEntity transactionDetails) {
		int transactionId = transactionDetailsService.addTransaction(transactionDetails);
		return  new ResponseEntity<>(transactionId, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/transaction/{id}")
	public ResponseEntity<TransactionDetailsEntity> getTransactionDetailsById(@PathVariable("id") int id){
		TransactionDetailsEntity transaction = transactionDetailsService.getTransactionById(id);
		return ResponseEntity.ok(transaction);
	}
	
	@GetMapping(value = "/{id}/booking")
	public ResponseEntity<TransactionDetailsEntity> getTransactionDetailsByBookingId(@PathVariable("id") int id){
		TransactionDetailsEntity transaction = transactionDetailsService.getTransactionByBookingId(id);
		return ResponseEntity.ok(transaction);
	}
	
	@GetMapping(value = "/transaction")
	public ResponseEntity<List<TransactionDetailsEntity>> getAllTransactionDetails(){
		List<TransactionDetailsEntity> list = new ArrayList<>();
		for(TransactionDetailsEntity transaction : transactionDetailsService.getAllTransactions().values()) {
			list.add(transaction);
		}
		return new ResponseEntity<List<TransactionDetailsEntity>>(list, HttpStatus.OK);
	}
	

}
