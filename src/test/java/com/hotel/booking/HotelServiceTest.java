package com.hotel.booking;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.HotelRepository;
import com.hotel.booking.service.HotelServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchHotels_Found() {
        // Arrange
        String location = "New York";
        List<Hotel> mockHotels = Arrays.asList(
                Hotel.builder()
                        .id(1L)
                        .name("Hotel A")
                        .location("New York")
                        .rating(4)
                        .build(),
                Hotel.builder()
                        .id(2L)
                        .name("Hotel B")
                        .location("New York")
                        .rating(5)
                        .build());

        when(hotelRepository.findByLocation(location)).thenReturn(mockHotels);

        // Act
        List<Hotel> result = hotelService.searchHotels(location);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        assertEquals("New York", result.get(0).getLocation());
        verify(hotelRepository, times(1)).findByLocation(location);
    }

    @Test
    void testSearchHotels_NotFound() {
        // Arrange
        String location = "Los Angeles";
        when(hotelRepository.findByLocation(location)).thenReturn(Arrays.asList());

        // Act
        List<Hotel> result = hotelService.searchHotels(location);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(hotelRepository, times(1)).findByLocation(location);
    }

    @Test
    void testGetAllHotels() {
        // Arrange
        List<Hotel> mockHotels = Arrays.asList(
                Hotel.builder()
                        .id(1L)
                        .name("Hotel A")
                        .location("New York")
                        .rating(4)
                        .build(),
                Hotel.builder()
                        .id(2L)
                        .name("Hotel B")
                        .location("New York")
                        .rating(5)
                        .build());

        when(hotelRepository.findAll()).thenReturn(mockHotels);

        // Act
        List<Hotel> result = hotelService.getAllHotels();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        assertEquals("New York", result.get(1).getLocation());
        verify(hotelRepository, times(1)).findAll();
    }
}
