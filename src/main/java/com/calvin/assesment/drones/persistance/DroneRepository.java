package com.calvin.assesment.drones.persistance;

import com.calvin.assesment.drones.domain.enums.State;
import com.calvin.assesment.drones.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findAllByState(State state);
    Drone findTopBySerialNumber(String serialNumber);
}
