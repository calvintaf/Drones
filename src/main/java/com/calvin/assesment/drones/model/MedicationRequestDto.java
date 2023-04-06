package com.calvin.assesment.drones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationRequestDto {

    private String name;

    private Double weight;

    private String code;

}
