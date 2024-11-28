# Hotel Booking System - API Documentation

## Overview

This project is a Hotel Booking System that allows users to:

- Search for hotels in specific locations.
- Create hotel bookings for selected dates.
- View booking details.
- Optionally edit bookings.

The backend is built using Spring Boot and uses PostgreSQL for persistent storage. The API endpoints expose basic hotel and booking operations.

## API Endpoints

### 1. Search for Hotels

- Endpoint: `GET /hotels/search`
- Description: Searches for hotels based on a given location.
- Query Parameters:
  - `location` (string): The destination city or location to search for hotels.
- Example Request:

```bash
GET /hotels/search?location=New York
```

- Example Response:

```json
[
  {
    "id": 1,
    "name": "Hotel A",
    "location": "New York",
    "rating": 4
  },
  {
    "id": 2,
    "name": "Hotel B",
    "location": "New York",
    "rating": 5
  }
]
```

### 2. Create Hotel Booking

- Endpoint: `POST /bookings`
- Description: Allows users to create a booking for a selected hotel with check-in and check-out dates.
- Request Body:

```json
{
  "userId": 1,
  "hotelId": 2,
  "checkInDate": "2024-12-01",
  "checkOutDate": "2024-12-05"
}
```

- Example Response:

```json
{
  "id": 1,
  "userId": 1,
  "hotelId": 2,
  "checkInDate": "2024-12-01",
  "checkOutDate": "2024-12-05",
  "status": "CONFIRMED"
}
```

### 3. Get Booking Details

- Endpoint: `GET /bookings/{id}`
- Description: Retrieves the details of a specific booking by its ID.
- Path Parameters:
  - `id` (long): The ID of the booking.
- Example Request:

```bash
GET /bookings/1
```

- Example Response:

```json
{
  "id": 1,
  "userId": 1,
  "hotelId": 2,
  "checkInDate": "2024-12-01",
  "checkOutDate": "2024-12-05",
  "status": "CONFIRMED"
}
```

### 4. Edit Hotel Booking

- Endpoint: `PUT /bookings/{id}`
- Description: Allows users to edit an existing booking's check-in and check-out dates.
- Path Parameters:
  - `id` (long): The ID of the booking to edit.
- Request Body:

```json
{
  "checkInDate": "2024-12-02",
  "checkOutDate": "2024-12-06"
}
```

- Example Response:

```json
{
  "id": 1,
  "userId": 1,
  "hotelId": 2,
  "checkInDate": "2024-12-02",
  "checkOutDate": "2024-12-06",
  "status": "CONFIRMED"
}
```

## Request and Response Model

### BookingRequest Model

Represents the request body when creating or editing a booking.

```java
public class BookingRequest {
    private Long userId;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
```

### Hotel Model

Represents the hotel data.

```java
public class Hotel {
    private Long id;
    private String name;
    private String location;
    private int rating;
}
```

### Booking Model

Represents a booking entry in the system.

```java
public class Booking {
    private Long id;
    private Long userId;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status; // e.g., "CONFIRMED", "CANCELLED"
}
```

## Database Configuration

- RDBMS: PostgreSQL is used to store data (hotels, bookings).
- Connection String Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_booking
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Unit Tests

The system includes unit tests to ensure the functionality of the booking and hotel-related services. These tests include:

- `BookingServiceTest`: Tests for creating, retrieving, and editing bookings.
- `HotelServiceTest`: Tests for searching hotels and retrieving all hotels.

Example of a unit test for `BookingService`:

```java
@Test
void testCreateBooking() {
    // Arrange
    BookingRequest request = new BookingRequest();
    request.setUserId(1L);
    request.setHotelId(2L);
    request.setCheckInDate(LocalDate.now());
    request.setCheckOutDate(LocalDate.now().plusDays(3));

    Booking savedBooking = new Booking();
    savedBooking.setId(1L);
    savedBooking.setUserId(request.getUserId());
    savedBooking.setHotelId(request.getHotelId());
    savedBooking.setCheckInDate(request.getCheckInDate());
    savedBooking.setCheckOutDate(request.getCheckOutDate());
    savedBooking.setStatus("CONFIRMED");

    when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

    // Act
    Booking result = bookingService.createBooking(request);

    // Assert
    assertNotNull(result);
    assertEquals("CONFIRMED", result.getStatus());
    assertEquals(1L, result.getUserId());
    assertEquals(2L, result.getHotelId());
}
```

## Running the Application

### Clone the repository:

```bash
git clone https://github.com/your-repository.git
```

### Navigate to the project directory:

```bash
cd hotel-booking-system
```

### Build the project: If using Maven:

```bash
mvn clean install
```

### Run the application:

```bash
mvn spring-boot:run
```

### Access the API:

Open Postman or any API testing tool.
Set the API endpoint to `http://localhost:8080`.

## Conclusion

This Hotel Booking System API provides endpoints for managing hotel bookings and hotel data. It utilizes Spring Boot for the backend and PostgreSQL for data persistence. The project includes unit tests, documentation, and a well-defined API structure for seamless integration.
