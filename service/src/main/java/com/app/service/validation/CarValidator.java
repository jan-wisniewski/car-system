package com.app.service.validation;

import com.app.persistence.model.Car;
import com.app.service.exceptions.CarValidatorException;
import com.app.service.validation.generic.AbstractValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarValidator extends AbstractValidator {


    @Override
    public Map<String, String> validate(Car item) {
        Map<String, String> errors = new HashMap<>();

        if (!isCorrectEnginePowerValue(item)) {
            errors.put("Engine", "Engine Power is negative");
        }

        if (!wheelModelHasOnlyUppercase(item)) {
            errors.put("Wheel", "Wheel model doesnt have only uppercase letters");
        }

        if (!wheelSizeIsPositive(item)) {
            errors.put("Wheel", "Wheel size is not positive");
        }

        if (!isComponentsListAreUppercase(item)) {
            errors.put("Components", "Components in components list" +
                    "should have only uppercase");
        }

        if (!isCarModelUppercase(item)) {
            errors.put("Model", "Car model is not only uppercase");
        }

        if (!isCarMileageIsPositive(item)) {
            errors.put("Mileage", "Car Mileage is not positive");
        }

        if (!isCarPriceIsPositive(item)) {
            errors.put("Price", "Car Price is not positive");
        }

        return errors;
    }

    private boolean isCarModelUppercase(Car item) {
        if (item == null) {
            throw new CarValidatorException("Car item is null");
        }
        return item.getModel().matches("([A-Z]+.?)+");
    }

    private boolean isCarPriceIsPositive(Car item) {
        if (item == null) {
            throw new CarValidatorException("Car item is null");
        }
        return item.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean isCarMileageIsPositive(Car item) {
        if (item == null) {
            throw new CarValidatorException("Car item is null");
        }
        return item.getMileage() >= 0;
    }

    private boolean isComponentsListAreUppercase(Car item) {
        if (item == null) {
            throw new CarValidatorException("Car item is null");
        }
        return item.getCarBody().getComponents()
                .stream()
                .allMatch(c -> c.matches("([A-Z]+.?)+"));
    }

    private boolean wheelSizeIsPositive(Car item) {
        if (item == null) {
            throw new CarValidatorException("Item is null");
        }
        return item.getWheel().getSize() >= 0;
    }

    private boolean wheelModelHasOnlyUppercase(Car item) {
        if (item == null) {
            throw new CarValidatorException("Car item is null");
        }
        return item.getWheel().getModel().matches("([A-Z]+?.)+");
    }

    private boolean isCorrectEnginePowerValue(Car item) {
        if (item == null) {
            throw new CarValidatorException("Item is null");
        }
        return item.getEngine().getPower() >= 0;
    }

}
