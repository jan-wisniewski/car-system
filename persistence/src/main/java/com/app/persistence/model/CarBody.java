package com.app.persistence.model;

import com.app.persistence.enums.CarBodyColor;
import com.app.persistence.enums.CarBodyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarBody {
    private CarBodyColor color;
    private CarBodyType type;
    private List<String> components;
}

