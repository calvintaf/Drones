package com.calvin.assesment.drones.service;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.exception.DroneLoadingException;
import com.calvin.assesment.drones.model.DroneRequestDto;

public interface DroneValidationService {
    void validateDrone(Drone drone, Medication medication) throws DroneLoadingException;
    void validateSerialNumber(DroneRequestDto droneRequestDto);

    void validateBatteryLevel(DroneRequestDto droneRequestDto);
}
