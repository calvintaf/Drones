package com.calvin.assesment.drones.service;

import com.calvin.assesment.drones.model.PackageRequestDto;
import com.calvin.assesment.drones.model.PackageResponseDto;
import java.util.List;

public interface PackageService {
    List<PackageResponseDto> processPackage(List<PackageRequestDto> packageRequestDtoList);
}
