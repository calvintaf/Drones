package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.domain.enums.State;
import com.calvin.assesment.drones.exception.RecordNotFoundException;
import com.calvin.assesment.drones.model.DroneRequestDto;
import com.calvin.assesment.drones.persistance.DroneRepository;
import com.calvin.assesment.drones.service.DroneService;
import com.calvin.assesment.drones.service.DroneValidationService;
import com.calvin.assesment.drones.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationService medicationService;
    private final DroneValidationService droneValidationService;

    @Override
    public Drone createDrone(DroneRequestDto droneRequestDto) {
        log.info("Adding new Drone: {}", droneRequestDto);

        droneValidationService.validateSerialNumber(droneRequestDto);
        droneValidationService.validateBatteryLevel(droneRequestDto);

        Drone drone =  new Drone();
        drone.setBatteryLevel(droneRequestDto.getBatteryCapacity());
        drone.setModel(droneRequestDto.getModel());
        drone.setSerialNumber(droneRequestDto.getSerialNumber());

        return droneRepository.save(drone);
    }

    @Override
    public Drone getDrone(Long droneId) {
        log.info("Retrieving Drone with ID {}", droneId);
        return droneRepository.findById(droneId).
                orElseThrow(() -> new RecordNotFoundException("Specified Drone not found."));
    }

    @Override
    public Drone updateDrone(Long droneId, DroneRequestDto droneRequestDto) {
        log.info("Updating Drone with ID: {}", droneId);
        Drone drone = droneRepository.
                findById(droneId).
                orElseThrow(() -> new RecordNotFoundException("Specified Drone not found."));
        droneValidationService.validateSerialNumber(droneRequestDto);
        droneValidationService.validateBatteryLevel(droneRequestDto);
        drone.setSerialNumber(droneRequestDto.getSerialNumber());
        drone.setBatteryLevel(droneRequestDto.getBatteryCapacity());
        drone.setModel(droneRequestDto.getModel());

        return droneRepository.save(drone);
    }

    @Override
    public Drone loadDrone(Long droneId, Long medicationId) {
        Medication medication = medicationService.getMedication(medicationId);
        Drone drone = droneRepository
                .findById(droneId)
                .orElseThrow(() -> new RecordNotFoundException("Specified Drone not found."));
        droneValidationService.validateDrone(drone, medication);
        drone.setMedication(medication);
        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAvailableDrones() {
        log.info("Retrieving available Drones");
        return droneRepository.findAllByState(State.IDLE);
    }

    @Override
    public List<Drone> getAllDrones() {
        log.info("Retrieving all Drones");
        return droneRepository.findAll();
    }


}
