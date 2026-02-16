package com.airport.pooling.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long rideId;
    private String name;
    private int luggage;
}
