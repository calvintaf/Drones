package com.calvin.assesment.drones.controller;

import com.calvin.assesment.drones.model.PackageRequestDto;
import com.calvin.assesment.drones.model.PackageResponseDto;
import com.calvin.assesment.drones.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @PostMapping
    public List<PackageResponseDto> processPackage(@RequestBody List<PackageRequestDto> packageRequestDtoList) {
        return packageService.processPackage(packageRequestDtoList);
    }
}