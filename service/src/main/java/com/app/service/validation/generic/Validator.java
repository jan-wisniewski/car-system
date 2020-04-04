package com.app.service.validation.generic;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T item);
    boolean hasErrors();
}
