package com.airport.pooling.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int luggageCount;
}
