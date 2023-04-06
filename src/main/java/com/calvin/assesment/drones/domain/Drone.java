package com.calvin.assesment.drones.domain;

import com.calvin.assesment.drones.domain.enums.Model;
import com.calvin.assesment.drones.domain.enums.State;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "serial_number", nullable = false, unique = true, length = 100)
    private String serialNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit", nullable = false, scale = 1)
    private Double weightLimit;


    @Column(name = "battery_level", nullable = false)
    private Integer batteryLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;

    public Drone() {
        setState(State.IDLE);
        setWeightLimit(500.0D);
    }
}
