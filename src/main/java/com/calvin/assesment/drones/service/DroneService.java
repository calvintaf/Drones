package com.calvin.assesment.drones.service;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.model.DroneRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DroneService {

    Drone createDrone(DroneRequestDto droneRequestDto);

    Drone getDrone(Long droneId);

    Drone updateDrone(Long droneId, DroneRequestDto droneRequestDto);

    Drone loadDrone(Long droneId, Long medicationId);

    List<Drone> getAvailableDrones();

    List<Drone> getAllDrones();
}
