package org.ncu.bookingService.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ncu.bookingService.entities.BookingInfoEntity;
import org.ncu.bookingService.exception.InvalidBookingIdException;
import org.ncu.bookingService.exception.InvalidPaymentModeException;
import org.ncu.bookingService.repository.BookingInfoRepository;
import org.ncu.bookingService.dto.BookingInfoDto;
import org.ncu.bookingService.dto.TransactionDetailsDto;
import org.ncu.bookingService.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingInfoServiceImpl implements BookingInfoService{
	
	@Autowired
	BookingInfoRepository bookingInfoRepository;
	
	@Autowired
    private RestTemplate restTemplate;

	public int calculateDuration(Date fromDate, Date toDate)
	{
		java.util.Date utilDate1 = new java.util.Date(fromDate.getTime());
		java.util.Date utilDate2 = new java.util.Date(toDate.getTime());
		long differenceInMilliseconds = utilDate2.getTime() - utilDate1.getTime();
		int diff = (int) TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
		return diff;
	}
	
	public int calculateRoomPrice(int num, int duration)
	{
		int price = 1000*num*duration;
		return price;
	}
	
	public String assignRooms(int num)
	{
		int min = 1;  
		int max = 100;
		String rooms = "";
		for(int i=0; i<num-1; i++)
		{
			int r = (int)(Math.random()*(max-min+1)+min);  
			rooms = rooms + r + ", ";
		}
		int r = (int)(Math.random()*(max-min+1)+min);  
		rooms = rooms + r;
		
		return rooms;
	}
	
	@Override
	public BookingInfoEntity newBooking(BookingInfoEntity bookingInfoEntity) {
		// TODO Auto-generated method stub

		int duration = calculateDuration(bookingInfoEntity.getFromDate(), bookingInfoEntity.getToDate());
		int price = calculateRoomPrice(bookingInfoEntity.getNumOfRooms(), duration);
		String rooms = assignRooms(bookingInfoEntity.getNumOfRooms());

		bookingInfoEntity.setRoomNumbers(rooms);
		bookingInfoEntity.setRoomPrice(price);
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		bookingInfoEntity.setBookedOn(date);

		BookingInfoEntity b = bookingInfoRepository.save(bookingInfoEntity);
		if (b == null)
			return null;
		return b;
	}

	@Override
	public Map<Integer, BookingInfoEntity> getAllBookings() {

		List<BookingInfoEntity> list = bookingInfoRepository.findAll();
		Map<Integer,BookingInfoEntity> map = new HashMap<Integer, BookingInfoEntity>();
		for(BookingInfoEntity book: list) {
			System.out.println(book);
			map.put(book.getBookingId(), book);
		}
		return map;
	}

	@Override
	public BookingInfoEntity finalizeBooking(int bookingId, TransactionDetailsDto transactionDto) {
		// TODO Auto-generated method stub

		String upi = "UPI";
		String card = "CARD";		
		if (!upi.equals(transactionDto.getPaymentMode()) && !card.equals(transactionDto.getPaymentMode())) {
			throw new InvalidPaymentModeException("Invalid mode of payment");
	    }
		
		if(bookingId!=transactionDto.getBookingId()) {
			throw new InvalidBookingIdException("Request URL Booking Id: "
						+ bookingId +" does not match Request Body Booking Id: " + transactionDto.getBookingId()+"!");
		}
		
		BookingInfoEntity booking = bookingInfoRepository.findById(bookingId).orElseThrow(
				() -> new InvalidBookingIdException("Invalid Booking Id"));
		
        ResponseEntity<Integer> transID = restTemplate.postForEntity("http://localhost:8083/payment/transaction", 
        		transactionDto, Integer.class);
        int id = transID.getBody();
        booking.setTransactionId(id);
        
        //Transaction Id updated in Booking Table
        bookingInfoRepository.save(booking);
        
        String message = "Booking confirmed for user with aadhaar number: "
        		+ booking.getAadharNumber()
        		+ " | "
        		+ "Here are the booking details: " + booking.toString();
        System.out.println(message);
		return booking;
	}
	
	
	private BookingInfoDto mapToBookingInfo(BookingInfoEntity booking){
		BookingInfoDto  bookingDto = new BookingInfoDto();
		bookingDto.setBookingId(booking.getBookingId());
		bookingDto.setFromDate(booking.getFromDate());
		bookingDto.setToDate(booking.getToDate());
		bookingDto.setAadharNumber(booking.getAadharNumber());
		bookingDto.setNumOfRooms(booking.getNumOfRooms());
		bookingDto.setRoomNumbers(booking.getRoomNumbers());
		bookingDto.setRoomPrice(booking.getRoomPrice());
		bookingDto.setTransactionId(booking.getTransactionId());
		bookingDto.setBookedOn(booking.getBookedOn());

        return bookingDto;
    }
	
	@Override
	public ResponseDto getBookingDetails(int bookingId) {
		// TODO Auto-generated method stub
		
		ResponseDto responseDto = new ResponseDto();
		BookingInfoEntity booking = bookingInfoRepository.findById(bookingId).orElseThrow(() -> new InvalidBookingIdException("Booking with id: "+bookingId+" is not present!!"));
        BookingInfoDto bookingDto = mapToBookingInfo(booking);
        ResponseEntity<TransactionDetailsDto> responseEntity = restTemplate.getForEntity("http://localhost:8083/payment/" + booking.getBookingId()+"/booking", TransactionDetailsDto.class);
        
        TransactionDetailsDto transactionDto = responseEntity.getBody();
        System.out.println(responseEntity.getStatusCode());
        
        responseDto.setBooking(bookingDto);
        responseDto.setTransaction(transactionDto);
        return responseDto;
	}

}
