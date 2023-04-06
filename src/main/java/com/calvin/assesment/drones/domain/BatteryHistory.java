package com.calvin.assesment.drones.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class BatteryHistory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false)
        private Long id;

        @Column(name = "serial_number", nullable = false,updatable = false)
        private String serialNumber;

        @Column(name = "description", nullable = false)
        private String description;

        @Column(name = "battery_level", nullable = false)
        private String batteryLevel;

        @Column(name = "date", nullable = false)
        private LocalDateTime date;

    }
