package com.hotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hotel.booking.dto.BookingRequest;
import com.hotel.booking.model.Booking;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.service.BookingServiceImpl;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBooking() {
        BookingRequest request = new BookingRequest();
        request.setUserId(1L);
        request.setHotelId(2L);
        request.setCheckInDate(LocalDate.now());
        request.setCheckOutDate(LocalDate.now().plusDays(3));

        Booking savedBooking = Booking.builder()
                .userId(request.getUserId())
                .hotelId(request.getHotelId())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .status("CONFIRMED")
                .build();

        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        Booking result = bookingService.createBooking(request);

        assertNotNull(result);
        assertEquals("CONFIRMED", result.getStatus());
        assertEquals(1L, result.getUserId());
        assertEquals(2L, result.getHotelId());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testGetBookingDetails_Found() {
        // Arrange
        Long bookingId = 1L;
        Booking existingBooking = Booking.builder()
                .id(bookingId)
                .userId(1L)
                .hotelId(2L)
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(3))
                .status("CONFIRMED")
                .build();

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));

        // Act
        Booking result = bookingService.getBookingDetails(bookingId);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(2L, result.getHotelId());
        verify(bookingRepository, times(1)).findById(bookingId);
    }

    @Test
    void testGetBookingDetails_NotFound() {
        // Arrange
        Long bookingId = 1L;
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.getBookingDetails(bookingId);
        });
        assertEquals("Booking not found!", exception.getMessage());
        verify(bookingRepository, times(1)).findById(bookingId);
    }

    @Test
    void testEditBooking() {
        // Arrange
        Long bookingId = 1L;
        Booking existingBooking = Booking.builder()
                .id(bookingId)
                .userId(1L)
                .hotelId(2L)
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(3))
                .status("CONFIRMED")
                .build();

        BookingRequest updateRequest = new BookingRequest();
        updateRequest.setCheckInDate(LocalDate.now().plusDays(1));
        updateRequest.setCheckOutDate(LocalDate.now().plusDays(4));

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(existingBooking);

        // Act
        Booking result = bookingService.editBooking(bookingId, updateRequest);

        // Assert
        assertNotNull(result);
        assertEquals(updateRequest.getCheckInDate(), result.getCheckInDate());
        assertEquals(updateRequest.getCheckOutDate(), result.getCheckOutDate());
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(existingBooking);
    }
}
