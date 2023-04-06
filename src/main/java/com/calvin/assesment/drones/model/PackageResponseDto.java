package com.calvin.assesment.drones.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Data
public class PackageResponseDto {
    private final Double sumWeight;
    private final Double sumPrice;
    private final List<Integer> ids;
}
