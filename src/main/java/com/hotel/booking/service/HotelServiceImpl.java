package com.hotel.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    /**
     * Constructor to initialize the HotelRepository.
     * 
     * @param hotelRepository the HotelRepository instance.
     */
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Searches hotels by location.
     * 
     * @param location the location to search hotels for.
     * @return List of hotels found at the specified location.
     */
    @Override
    public List<Hotel> searchHotels(String location) {
        return hotelRepository.findByLocation(location);
    }

    /**
     * Retrieves all hotels.
     * 
     * @return List of all hotels.
     */
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
