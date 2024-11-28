package com.hotel.booking.repository;
import com.hotel.booking.model.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository handles database operations related to bookings.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    /**
     * Finds all bookings by user id.
     * @param userId the id of the user.
     * @return List of bookings made by the user.
     */
    List<Booking> findByUserId(Long userId);
}
