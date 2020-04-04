package com.app.service.validation.generic;

import com.app.persistence.model.Car;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractValidator implements Validator<Car> {
    protected Map<String, String> errors = new HashMap<>();

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
