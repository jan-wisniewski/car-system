package com.app.service;

import com.app.persistence.converter.JsonCarsConverter;
import com.app.persistence.model.Car;
import com.app.service.enums.SortCriterion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Car> findCarsContainingAllComponents(List<String> components) {
        if (components == null) {
            throw new IllegalArgumentException("Components list is null");
        }
        return cars
                .stream()
                .filter(c -> c.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    public List<Car> sortCars(SortCriterion criterion, boolean ascendingSort) {
        if (criterion == null) {
            throw new IllegalArgumentException("Sort Criterion is null");
        }
        Stream<Car> carStream =
                switch (criterion) {
                    case ENGINE -> cars.stream().sorted(Comparator.comparing(c -> c.getEngine().getPower()));
                    case WHEEL -> cars.stream().sorted(Comparator.comparing(c -> c.getWheel().getSize()));
                    case COMPONENTS -> cars.stream().sorted(Comparator.comparing(c -> c.getCarBody().getComponents().size()));
                    default -> cars.stream().sorted(Comparator.comparing(Car::getPrice));
                };
        List<Car> carsList = carStream.collect(Collectors.toList());
        if (ascendingSort) {
            Collections.reverse(carsList);
        }
        return carsList;
    }

}

