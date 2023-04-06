package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.BatteryHistory;
import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.persistance.BatteryHistoryRepository;
import com.calvin.assesment.drones.service.BatteryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {

    private final BatteryHistoryRepository batteryHistoryRepository;

    @Override
    public void createBatteryLog(Drone drone){
        log.info("Logging Battery history for Drone {}"+ drone);
        BatteryHistory batteryHistory = new BatteryHistory();
        batteryHistory.setBatteryLevel(drone.getBatteryLevel().toString()+"%");
        batteryHistory.setDate(LocalDateTime.now());
        batteryHistory.setSerialNumber(drone.getSerialNumber());
        batteryHistory.setDescription(drone.getModel()+"/"+drone.getState());
        batteryHistoryRepository.save(batteryHistory);
    }
}
