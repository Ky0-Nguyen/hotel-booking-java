-- Create the "hotels" table
CREATE TABLE hotels (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5) -- Rating must be between 1 and 5
);

-- Create the "bookings" table
CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL, -- Reference to the user (for simplicity, assume users exist elsewhere)
    hotel_id BIGINT NOT NULL REFERENCES hotels(id) ON DELETE CASCADE, -- Foreign key to hotels table
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('CONFIRMED', 'CANCELLED')), -- Restrict status values
    CONSTRAINT check_dates CHECK (check_out_date > check_in_date) -- Ensure valid date range
);

-- Insert mock data into "hotels"
INSERT INTO hotels (name, location, rating) VALUES
('Hotel A', 'New York', 4),
('Hotel B', 'Los Angeles', 5),
('Hotel C', 'Chicago', 3);

-- Insert mock data into "bookings"
INSERT INTO bookings (user_id, hotel_id, check_in_date, check_out_date, status) VALUES
(1, 1, '2024-12-01', '2024-12-05', 'CONFIRMED'),
(2, 2, '2024-12-10', '2024-12-15', 'CANCELLED');
