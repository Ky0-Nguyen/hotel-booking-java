package com.hotel.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder(toBuilder = true)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int rating;

    // // Constructor
    // public Hotel() {
    // }

    // public Hotel(Long id, String name, String location, int rating) {
    // this.id = id;
    // this.name = name;
    // this.location = location;
    // this.rating = rating;
    // }

    // // Getters and setters
    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getLocation() {
    // return location;
    // }

    // public void setLocation(String location) {
    // this.location = location;
    // }

    // public int getRating() {
    // return rating;
    // }

    // public void setRating(int rating) {
    // this.rating = rating;
    // }
}
