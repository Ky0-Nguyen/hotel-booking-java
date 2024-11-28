package com.hotel.booking.controller;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles HTTP requests related to hotels.
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    /**
     * Constructor to initialize HotelService.
     * @param hotelService instance of HotelService.
     */
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * Handles GET request to retrieve all hotels.
     * @return List of all hotels.
     */
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    /**
     * Handles GET request to search hotels by location.
     * @param location the location to search hotels for.
     * @return List of hotels found at the specified location.
     */
    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String location) {
        return hotelService.searchHotels(location);
    }
}
