package com.hotel.booking.dto;

public class HotelSearchRequest {
    private String location;
    private Integer rating;

    // Constructors
    public HotelSearchRequest() {
    }

    public HotelSearchRequest(String location, Integer rating) {
        this.location = location;
        this.rating = rating;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "HotelSearchRequest{" +
                "location='" + location + '\'' +
                ", rating=" + rating +
                '}';
    }
}
