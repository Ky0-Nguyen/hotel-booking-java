package com.hotel.booking.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Builder(toBuilder = true)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long hotelId;
    private String userName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;

    public static class BookingBuilder {
        public BookingBuilder validateDates() {
            if (checkInDate != null && checkOutDate != null && checkOutDate.isBefore(checkInDate)) {
                throw new IllegalArgumentException("Check-out date must be after check-in date.");
            }
            return this;
        }
    }
}
