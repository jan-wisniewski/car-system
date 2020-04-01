package com.app.persistence.model;

import com.app.persistence.enums.TyreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Wheel {
    private String model;
    private int size;
    private TyreType type;
}
