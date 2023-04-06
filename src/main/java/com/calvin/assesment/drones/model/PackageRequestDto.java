package com.calvin.assesment.drones.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PackageRequestDto {
    private final Integer id;
    private final Double price;
    private final Double weight;
}
