package com.hotel.booking.service;

import com.hotel.booking.dto.BookingRequest;
import com.hotel.booking.model.Booking;
import com.hotel.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(BookingRequest request) {
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .hotelId(request.getUserId())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .build();
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingDetails(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));
    }

    @Override
    public Booking editBooking(Long id, BookingRequest request) {
        Booking booking = getBookingDetails(id);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        return bookingRepository.save(booking);
    }
}
