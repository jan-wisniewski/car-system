package com.app.persistence.converter;

import com.app.persistence.converter.generic.JsonConverter;
import com.app.persistence.model.Car;

import java.util.List;

public class JsonCarsConverter extends JsonConverter<List<Car>> {
    public JsonCarsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
