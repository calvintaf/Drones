package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.exception.MedicationValidationException;
import com.calvin.assesment.drones.service.MedicationValidationService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MedicationValidationServiceImpl implements MedicationValidationService {

    @Override
    public void validateName(String name) {
        validateMedication(name, "NAME");
    }

    @Override
    public void validateCode(String code) {
        validateMedication(code, "CODE");
    }

    private void validateMedication(String str, String type) {
        if (type.toUpperCase(Locale.ROOT).equals("NAME")) {
            if (!str.matches("^[a-zA-Z0-9_-]*$")) {
                throw new MedicationValidationException("Invalid Medication Name format");
            }
        } else if (type.toUpperCase(Locale.ROOT).equals("CODE")) {
            if (!str.matches("^[a-zA-Z0-9_]*$")) {
                throw new MedicationValidationException("Invalid Medication Code format");
            }
        }
    }
}
