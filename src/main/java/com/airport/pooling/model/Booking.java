package com.airport.pooling.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    private Ride ride;

    @ManyToOne
    private Passenger passenger;

    private boolean cancelled = false;
}
