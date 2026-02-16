package com.airport.pooling.service;

import com.airport.pooling.dto.BookingRequest;
import com.airport.pooling.model.*;
import com.airport.pooling.repository.*;
import com.airport.pooling.util.PricingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final RideRepository rideRepository;
    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(BookingRequest request) {

        Ride ride = rideRepository.findById(request.getRideId())
                .orElseThrow();

        if (ride.getAvailableSeats() <= 0)
            throw new RuntimeException("No seats available");

        if (ride.getCurrentLuggage() + request.getLuggage() > ride.getMaxLuggage())
            throw new RuntimeException("Luggage limit exceeded");

        ride.setAvailableSeats(ride.getAvailableSeats() - 1);
        ride.setCurrentLuggage(ride.getCurrentLuggage() + request.getLuggage());

        Passenger passenger = new Passenger();
        passenger.setName(request.getName());
        passenger.setLuggageCount(request.getLuggage());
        passengerRepository.save(passenger);

        double price = PricingUtil.calculatePrice(200,
                ride.getTotalSeats() - ride.getAvailableSeats());

        Booking booking = new Booking();
        booking.setRide(ride);
        booking.setPassenger(passenger);
        booking.setPrice(price);

        rideRepository.save(ride);
        return bookingRepository.save(booking);
    }
}
