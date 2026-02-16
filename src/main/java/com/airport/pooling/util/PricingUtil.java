package com.airport.pooling.util;

public class PricingUtil {

    public static double calculatePrice(double baseFare, int occupiedSeats) {
        double surgeMultiplier = 1 + (occupiedSeats * 0.05);
        return baseFare * surgeMultiplier;
    }
}
