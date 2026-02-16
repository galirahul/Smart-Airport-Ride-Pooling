package com.airport.pooling.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;

    private int totalSeats;
    private int availableSeats;

    private int maxLuggage;
    private int currentLuggage;

    @Version
    private Long version;
}
