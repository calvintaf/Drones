package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.exception.MedicationImageUploadException;
import com.calvin.assesment.drones.exception.RecordNotFoundException;
import com.calvin.assesment.drones.model.MedicationRequestDto;
import com.calvin.assesment.drones.persistance.MedicationRepository;
import com.calvin.assesment.drones.service.MedicationService;
import com.calvin.assesment.drones.service.MedicationValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationValidationService medicationValidationService;

    @Override
    public Medication createMedication(MedicationRequestDto medicationRequestDto) {

        log.info("Adding new Medication: {}", medicationRequestDto);
        medicationValidationService.validateName(medicationRequestDto.getName());
        medicationValidationService.validateCode(medicationRequestDto.getCode());

        Medication medication = new Medication();
        medication.setName(medicationRequestDto.getName());
        medication.setCode(medicationRequestDto.getCode());
        medication.setWeight(medicationRequestDto.getWeight());

        return medicationRepository.save(medication);

    }

    @Override
    public Medication getMedication(Long medicationId) {
        log.info("Retrieving Medication with ID: {}", medicationId);
        return medicationRepository.findById(medicationId).
                orElseThrow(() -> new RecordNotFoundException("Specified Medication not found"));
    }

    @Override
    public Medication updateMedication(Long medicationId, MedicationRequestDto medicationRequestDto) {
        log.info("Updating Medication with ID: {}", medicationId);
        Medication medication = medicationRepository.findById(medicationId).
                orElseThrow(() -> new RecordNotFoundException("Specified Medication not found"));
        medication.setWeight(medicationRequestDto.getWeight());
        medication.setName(medicationRequestDto.getName());
        medication.setCode(medicationRequestDto.getCode().toUpperCase(Locale.ROOT));

        return medicationRepository.save(medication);
    }

    @Override
    public Medication uploadImage(Long medicationId, MultipartFile image) {
        log.info("Uploading image in Medication with ID: {}", medicationId);
        Medication medication = medicationRepository.findById(medicationId).
                orElseThrow(() -> new RecordNotFoundException("Specified Medication not found"));
        byte[] imageBytes = getBytesFromImage(image);
        medication.setImage(imageBytes);
        return medicationRepository.save(medication);
    }

    private byte[] getBytesFromImage(MultipartFile image) {
        try {
            return image.getBytes();
        } catch (IOException e) {
            throw new MedicationImageUploadException("Failed to upload Image");
        }
    }

}
