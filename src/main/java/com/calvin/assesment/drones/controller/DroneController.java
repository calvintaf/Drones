package com.calvin.assesment.drones.controller;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.model.DroneRequestDto;
import com.calvin.assesment.drones.service.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/drone")
public class DroneController {

    private final DroneService droneService;

    @PostMapping
    public Drone createDrone(@RequestBody DroneRequestDto droneRequestDto){
        return droneService.createDrone(droneRequestDto);
    }

    @GetMapping
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    @GetMapping("/available")
    public List<Drone> getAvailableDrone(Pageable pageable){
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{id}")
    public Drone getDrone(@PathVariable Long id){
        return droneService.getDrone(id);
    }

    @PutMapping("/{id}")
    public Drone updateDrone(@PathVariable Long id, @RequestBody DroneRequestDto droneRequestDto){
        return droneService.updateDrone(id, droneRequestDto);
    }

    @PutMapping("/{id}/medication/{medicationId}")
    public Drone loadDrone(@PathVariable Long id,
                           @PathVariable Long medicationId){
        return droneService.loadDrone(id, medicationId);
    }

    //TODO Add Controller Advice for informative errors to Front End
}
