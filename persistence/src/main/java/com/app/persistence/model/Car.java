package com.app.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Car {
    private String model;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;
}