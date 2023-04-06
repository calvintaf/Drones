package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.Drone;
import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.domain.enums.Model;
import com.calvin.assesment.drones.domain.enums.State;
import com.calvin.assesment.drones.exception.RecordNotFoundException;
import com.calvin.assesment.drones.model.DroneRequestDto;
import com.calvin.assesment.drones.persistance.DroneRepository;
import com.calvin.assesment.drones.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private DroneServiceImpl droneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void createDrone() {
        DroneRequestDto droneRequestDto = new DroneRequestDto();
        droneRequestDto.setBatteryCapacity(80);
        droneRequestDto.setModel(Model.LIGHTWEIGHT);
        droneRequestDto.setSerialNumber("456");
        droneRequestDto.setWeightLimit(500D);

        Drone drone = new Drone();
        drone.setBatteryLevel(droneRequestDto.getBatteryCapacity());
        drone.setModel(droneRequestDto.getModel());
        drone.setSerialNumber(droneRequestDto.getSerialNumber());

        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        Drone savedDrone = droneService.createDrone(droneRequestDto);

        assertNotNull(savedDrone);
        assertEquals(drone.getBatteryLevel(), savedDrone.getBatteryLevel());
        assertEquals(drone.getModel(), savedDrone.getModel());
        assertEquals(drone.getSerialNumber(), savedDrone.getSerialNumber());
    }

    @Test
    void getDrone() {
        Drone mockDrone = new Drone();
        Long droneId = 123L;
        mockDrone.setId(droneId);
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(mockDrone));

        Drone result = droneService.getDrone(droneId);
        assertEquals(mockDrone, result);
    }

    @Test
    void updateDrone() {

        Long droneId = 1L;
        DroneRequestDto requestDto = createRequestDto();
        Drone existingDrone = new Drone();
        existingDrone.setId(droneId);
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(existingDrone));
        when(droneRepository.save(existingDrone)).thenReturn(existingDrone);

        Drone result = droneService.updateDrone(droneId, requestDto);

        verify(droneRepository).findById(droneId);
        verify(droneRepository).save(existingDrone);
        assertEquals(requestDto.getSerialNumber(), result.getSerialNumber());
        assertEquals(requestDto.getBatteryCapacity(), result.getBatteryLevel());
        assertEquals(requestDto.getModel(), result.getModel());
        assertEquals(requestDto.getWeightLimit(), result.getWeightLimit());
    }

    @Test
    public void updateDrone_nonExistingDrone_shouldThrowException() {

        Long droneId = 1L;
        DroneRequestDto requestDto = createRequestDto();
        when(droneRepository.findById(droneId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> droneService.updateDrone(droneId, requestDto));
    }

    @Test
    public void getAvailableDrones_shouldReturnIdleDrones() {

        List<Drone> drones = new ArrayList<>();
        Drone idleDrone1 = new Drone();
        Drone idleDrone2 = new Drone();
        Drone busyDrone = new Drone();
        idleDrone1.setState(State.IDLE);
        idleDrone2.setState(State.IDLE);
        busyDrone.setState(State.RETURNING);
        drones.add(idleDrone1);
        drones.add(idleDrone2);
        drones.add(busyDrone);
        when(droneRepository.findAllByState(State.IDLE)).thenReturn(drones);

        List<Drone> result = droneService.getAvailableDrones();

        verify(droneRepository).findAllByState(State.IDLE);
        assertEquals(3, result.size());
        assertTrue(result.contains(idleDrone1));
        assertTrue(result.contains(idleDrone2));
    }

    @Test
    public void loadDrone_validRequest_shouldSaveDrone() {

        Long droneId = 1L;
        Long medicationId = 2L;
        Medication medication = new Medication();
        Drone existingDrone = new Drone();
        existingDrone.setId(droneId);
        when(medicationService.getMedication(medicationId)).thenReturn(medication);
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(existingDrone));
        when(droneRepository.save(existingDrone)).thenReturn(existingDrone);

        Drone result = droneService.loadDrone(droneId, medicationId);

        verify(medicationService).getMedication(medicationId);
        verify(droneRepository).findById(droneId);
        verify(droneRepository).save(existingDrone);
        assertEquals(medication, result.getMedication());
    }

    @Test
    void getAllDrones() {
        List<Drone> drones = new ArrayList<>();
        Drone drone1 = new Drone();
        Drone drone2 = new Drone();
        drones.add(drone1);
        drones.add(drone2);
        when(droneRepository.findAll()).thenReturn(drones);

        List<Drone> result = droneService.getAllDrones();

        verify(droneRepository).findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(drone1));
        assertTrue(result.contains(drone2));
    }
    private DroneRequestDto createRequestDto() {
        DroneRequestDto requestDto = new DroneRequestDto();
        requestDto.setSerialNumber("SER123");
        requestDto.setBatteryCapacity(100);
        requestDto.setModel(Model.MIDDLEWEIGHT);
        requestDto.setWeightLimit(2.0);
        return requestDto;
    }
}