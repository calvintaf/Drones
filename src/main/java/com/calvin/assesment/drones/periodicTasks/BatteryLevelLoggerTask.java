package com.calvin.assesment.drones.periodicTasks;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.service.BatteryService;
import com.calvin.assesment.drones.service.DroneService;
import com.calvin.assesment.drones.service.FileWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BatteryLevelLoggerTask {

    private final DroneService droneService;
    private final FileWriterService fileWriterService;
    private final BatteryService batteryService;
    private static final String filepath = "batterylog.txt";

    @Scheduled(cron = "0 */30 * * * *") //Runs every half an hour
    public void logBatteryLevel() {

        List<Drone> droneList = droneService.getAllDrones();

        for (Drone drone : droneList) {
            batteryService.createBatteryLog(drone);
            logToTextFile(drone);
        }
    }

    private void logToTextFile(Drone drone) {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now() + "/");
        sb.append(drone.getId() + "/");
        sb.append(drone.getModel() + "/");
        sb.append(drone.getSerialNumber() + "/");
        sb.append("Battery level ====> ");
        sb.append(drone.getBatteryLevel() + "%");
        fileWriterService.writeToLog(sb.toString(), filepath);
    }
}
