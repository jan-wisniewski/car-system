package com.app.service;

import com.app.persistence.converter.JsonCarsConverter;
import com.app.persistence.model.Car;
import com.app.service.enums.SortCriterion;
import com.app.service.enums.StatsCriterion;
import com.app.service.exceptions.CarsServiceException;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import java.util.*;
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

    public String showCarsStats(StatsCriterion statsCriterion) {
        if (statsCriterion == null) {
            throw new CarsServiceException("Stats Criterion is null");
        }
        return switch (statsCriterion) {
            case PRICE -> carStatsPrice();
            case MILEAGE -> carStatsMileage();
            case ENGINE -> carStatsPowerEngine();
        };
    }

    public String carStatsPowerEngine() {
        if (cars==null){
            throw new CarsServiceException("Cars Set is null");
        }
        StringBuilder sb = new StringBuilder();
        DoubleSummaryStatistics engineStats = cars
                .stream()
                .collect(Collectors.summarizingDouble(c -> c.getEngine().getPower()));
        sb.append("MIN ENGINE POWER: ").append(engineStats.getMin()).append("\n");
        sb.append("MAX ENGINE POWER: ").append(engineStats.getMax()).append("\n");
        sb.append("AVERAGE ENGINE POWER: ").append(engineStats.getAverage()).append("\n");
        return sb.toString();
    }

    public String carStatsMileage() {
        if (cars==null){
            throw new CarsServiceException("Cars Set is null");
        }
        StringBuilder sb = new StringBuilder();
        IntSummaryStatistics mileageStats = cars
                .stream()
                .collect(Collectors.summarizingInt(Car::getMileage));
        sb.append("MIN MILEAGE: ").append(mileageStats.getMin()).append("\n");
        sb.append("MAX MILEAGE: ").append(mileageStats.getMax()).append("\n");
        sb.append("AVERAGE MILEAGE: ").append(mileageStats.getAverage()).append("\n");
        return sb.toString();
    }

    public String carStatsPrice() {
        if (cars==null){
            throw new CarsServiceException("Cars Set is null");
        }
        StringBuilder sb = new StringBuilder();
        BigDecimalSummaryStatistics priceStats = cars.stream()
                .collect(Collectors2.summarizingBigDecimal(Car::getPrice));
        sb.append("MIN PRICE: ").append(priceStats.getMin()).append("\n");
        sb.append("MAX PRICE: ").append(priceStats.getMax()).append("\n");
        sb.append("AVERAGE PRICE: ").append(priceStats.getAverage()).append("\n");
        return sb.toString();
    }

}

