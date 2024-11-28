package com.hotel.booking.controller;

import com.hotel.booking.dto.BookingRequest;
import com.hotel.booking.model.Booking;
import com.hotel.booking.service.BookingService;

import org.springframework.web.bind.annotation.*;

// Annotation to mark this class as a web request handler
@RestController
// Mapping for all endpoints in this controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    // Constructor for BookingController
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
  
    // Endpoint to create a new booking
    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    // Endpoint to get details of a booking by its ID
    @GetMapping("/{id}")
    public Booking getBookingDetails(@PathVariable Long id) {
        return bookingService.getBookingDetails(id);
    }

    // Endpoint to edit an existing booking
    @PutMapping("/{id}")
    public Booking editBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        return bookingService.editBooking(id, request);
    }
}