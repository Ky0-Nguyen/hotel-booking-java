package com.hotel.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int rating;

    public Hotel() {
    }

    public Hotel(Long id, String name, String location, int rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
    }
}
