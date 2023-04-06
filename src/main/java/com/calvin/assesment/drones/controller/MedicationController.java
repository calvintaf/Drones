package com.calvin.assesment.drones.controller;

import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.model.MedicationRequestDto;
import com.calvin.assesment.drones.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping
    public Medication createMedication(@RequestBody MedicationRequestDto medicationRequestDto){
        return medicationService.createMedication(medicationRequestDto);
    }

    @GetMapping("/{medicationId}")
    public Medication getMedication(@PathVariable Long medicationId){
        return medicationService.getMedication(medicationId);
    }

    @PutMapping("/{medicationId}")
    public Medication updateMedication(@PathVariable Long medicationId, @RequestBody MedicationRequestDto medicationRequestDto){
        return medicationService.updateMedication(medicationId, medicationRequestDto);
    }

    @PutMapping("/{medicationId}/image")
    public Medication uploadImage (@PathVariable Long medicationId, @RequestParam("file") MultipartFile image){
        return medicationService.uploadImage(medicationId, image);
    }
}
