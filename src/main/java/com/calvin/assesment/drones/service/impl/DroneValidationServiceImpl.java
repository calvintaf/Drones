package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.domain.enums.State;
import com.calvin.assesment.drones.exception.DroneException;
import com.calvin.assesment.drones.exception.DroneLoadingException;
import com.calvin.assesment.drones.exception.InvalidSerialNumberException;
import com.calvin.assesment.drones.model.DroneRequestDto;
import com.calvin.assesment.drones.persistance.DroneRepository;
import com.calvin.assesment.drones.service.DroneValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DroneValidationServiceImpl implements DroneValidationService {

    private final DroneRepository droneRepository;

    private static final int MINIMUM_BATTERY_CAPACITY = 0;
    private static final int MAXIMUM_BATTERY_CAPACITY = 100;
    private static final int MINIMUM_LOADING_BATTERY_LEVEL = 25;//TODO Ideally this value should be parameterised as a configuration incase rules change

    @Override
    public void validateDrone(Drone drone, Medication medication) throws DroneLoadingException {
        if (medication.getWeight() > drone.getWeightLimit()) {
            throw new DroneLoadingException("Medication weight exceeds drone capacity");
        }

        if (!drone.getState().equals(State.IDLE)) {
            throw new DroneLoadingException("Drone state is not eligible for loading");
        }

        if (drone.getBatteryLevel() < MINIMUM_LOADING_BATTERY_LEVEL) {
            throw new DroneLoadingException("Drone battery capacity is too low");
        }
    }

    @Override
    public void validateSerialNumber(DroneRequestDto droneRequestDto) {
        Drone drone = droneRepository.findTopBySerialNumber(droneRequestDto.getSerialNumber());
        if (drone == null) {
            return;
        }
        if (!drone.getSerialNumber().equalsIgnoreCase(droneRequestDto.getSerialNumber())) {
            throw new InvalidSerialNumberException("Enter a unique serial number");
        }
    }

    @Override
    public void validateBatteryLevel(DroneRequestDto droneRequestDto) {
        if (droneRequestDto.getBatteryCapacity() < MINIMUM_BATTERY_CAPACITY || droneRequestDto.getBatteryCapacity() > MAXIMUM_BATTERY_CAPACITY) {
            throw new DroneException("Invalid battery level");
        }
    }
}
