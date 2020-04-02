package com.app.service;

import com.app.persistence.converter.JsonCarsConverter;
import com.app.persistence.model.Car;

import java.util.Set;
import java.util.stream.Collectors;

public class CarsService {

    private final Set<Car> cars;

    public CarsService(String jsonFileName) {
        this.cars = init(jsonFileName);
    }

    private Set<Car> init(String jsonFileName) {
        JsonCarsConverter jsonCarsConverter = new JsonCarsConverter(jsonFileName);
        return jsonCarsConverter.fromJson()
                .orElseThrow()
                .stream()
                .collect(Collectors.toSet());
    }

}

