package org.ncu.bookingService.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.ncu.bookingService.entities.BookingInfoEntity;
import org.ncu.bookingService.services.BookingInfoService;
import org.ncu.bookingService.dto.ResponseDto;
import org.ncu.bookingService.dto.TransactionDetailsDto;


@RestController
@RequestMapping("booking")
public class BookingInfoController {
	
	@Autowired
	BookingInfoService bookingInfoService;

	@PostMapping(path = "/new")
	public ResponseEntity<BookingInfoEntity> insertNewBooking(@RequestBody BookingInfoEntity booking) {
		BookingInfoEntity b = bookingInfoService.newBooking(booking);
		return new ResponseEntity<>(b, HttpStatus.CREATED);		
	}
	
	
	@PostMapping(path = "/{bookingId}/transaction")
	public ResponseEntity<BookingInfoEntity> getBookingDetails(@PathVariable("bookingId") int bookingId, @RequestBody TransactionDetailsDto transactionDto) {
		BookingInfoEntity b = bookingInfoService.finalizeBooking(bookingId, transactionDto);
		return new ResponseEntity<>(b, HttpStatus.CREATED);
	}
	
	
	@GetMapping(path = "/details/{bookingId}")
	public ResponseEntity<ResponseDto> getAllDetails(@PathVariable("bookingId") int bookingId) {
		ResponseDto responseDto = bookingInfoService.getBookingDetails(bookingId);
		return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<BookingInfoEntity>> getBookingInfos(){
		List<BookingInfoEntity> list = new ArrayList<>();
		for(BookingInfoEntity booking : bookingInfoService.getAllBookings().values()) {
			list.add(booking);
		}
		return new ResponseEntity<List<BookingInfoEntity>>(list, HttpStatus.OK);
	}
	
	
}
