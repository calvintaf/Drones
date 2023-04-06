package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.model.PackageRequestDto;
import com.calvin.assesment.drones.model.PackageResponseDto;
import com.calvin.assesment.drones.service.PackageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    @Override
    public List<PackageResponseDto> processPackage(List<PackageRequestDto> packageRequestDtoList) {
        return null;
    }
}
