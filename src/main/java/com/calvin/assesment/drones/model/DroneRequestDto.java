package com.calvin.assesment.drones.model;

import com.calvin.assesment.drones.domain.enums.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneRequestDto {

    private String serialNumber;

    private Model model;

    private Double weightLimit;

    private Integer batteryCapacity;

}
