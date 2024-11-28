package com.hotel.booking.repository;

import com.hotel.booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This repository handles database operations related to hotels.
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    /**
     * Finds all hotels by location.
     * @param location the location to search hotels for.
     * @return List of hotels found at the specified location.
     */
    List<Hotel> findByLocation(String location);
}
