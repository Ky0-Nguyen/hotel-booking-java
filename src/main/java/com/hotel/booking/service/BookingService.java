package com.hotel.booking.service;

import com.hotel.booking.dto.BookingRequest;
import com.hotel.booking.model.Booking;

public interface BookingService {
    
    Booking createBooking(BookingRequest request);
    
    Booking getBookingDetails(Long id);
    
    Booking editBooking(Long id, BookingRequest request);
}
