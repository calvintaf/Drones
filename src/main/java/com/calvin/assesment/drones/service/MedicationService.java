package com.calvin.assesment.drones.service;

import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.model.MedicationRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface MedicationService {

    Medication createMedication(MedicationRequestDto medicationRequestDto);;

    Medication getMedication(Long MedicationId);

    Medication updateMedication(Long medicationId, MedicationRequestDto medicationRequestDto);

    Medication uploadImage(Long medicationId, MultipartFile image);

}
