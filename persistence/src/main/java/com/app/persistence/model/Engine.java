package com.app.persistence.model;

import com.app.persistence.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Engine {
    private EngineType type;
    private double power;
}