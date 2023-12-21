package org.ncu.bookingService.services;

import java.util.Map;

import org.ncu.bookingService.dto.ResponseDto;
import org.ncu.bookingService.dto.TransactionDetailsDto;
import org.ncu.bookingService.entities.BookingInfoEntity;

public interface BookingInfoService {
	
	public BookingInfoEntity newBooking(BookingInfoEntity bookingInfoEntity);
	public Map<Integer,BookingInfoEntity> getAllBookings();
	public ResponseDto getBookingDetails(int id);
//	public BookingInfoEntity getBookingDetails(int bookingId)
	public BookingInfoEntity finalizeBooking(int id, TransactionDetailsDto transactionDto);


//	public String insertOrder(Order order);
//	public Map<Integer, Order> fetchAllOrders();
//	public Order updateOrder(int id, Order order);
//	public void deleteOrder(int id);
//	public ResponseDto getOrder(int id);
}
