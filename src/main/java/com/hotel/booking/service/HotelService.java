package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.model.Hotel;

public interface HotelService {
    List<Hotel> searchHotels(String location);

    List<Hotel> getAllHotels();
}
