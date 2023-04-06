package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.domain.Medication;
import com.calvin.assesment.drones.exception.MedicationImageUploadException;
import com.calvin.assesment.drones.exception.RecordNotFoundException;
import com.calvin.assesment.drones.model.MedicationRequestDto;
import com.calvin.assesment.drones.persistance.MedicationRepository;
import com.calvin.assesment.drones.service.MedicationService;
import com.calvin.assesment.drones.service.MedicationValidationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


class MedicationServiceImplTest {

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationValidationService medicationValidationService;

    @InjectMocks
    private MedicationService medicationService;

    @Disabled
    @Test
    public void testCreateMedication() {
        MedicationRequestDto dto = new MedicationRequestDto();
        dto.setName("Test Medication");
        dto.setCode("TM001");
        dto.setWeight(100.0);

        Medication mockMedication = new Medication();
        mockMedication.setId(1L);
        mockMedication.setName(dto.getName());
        mockMedication.setCode(dto.getCode());
        mockMedication.setWeight(dto.getWeight());

        Mockito.when(medicationRepository.save(Mockito.any())).thenReturn(mockMedication);

        Medication resultMedication = medicationService.createMedication(dto);

        Mockito.verify(medicationValidationService).validateName(dto.getName());
        Mockito.verify(medicationValidationService).validateCode(dto.getCode());

        assertNotNull(resultMedication);
        assertEquals(dto.getName(), resultMedication.getName());
        assertEquals(dto.getCode(), resultMedication.getCode());
        assertEquals(dto.getWeight(), resultMedication.getWeight(), 0);
    }

    @Test
    public void testGetMedicationNotFound() {
        Mockito.when(medicationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            medicationService.getMedication(1L);
        });

        assertEquals("Requested Medication not found", exception.getMessage());
    }

    @Test
    public void testGetMedication() {
        Medication mockMedication = new Medication();
        mockMedication.setId(1L);
        mockMedication.setName("Test Medication");
        mockMedication.setCode("TM001");
        mockMedication.setWeight(100.0);

        Mockito.when(medicationRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(mockMedication));

        Medication resultMedication = medicationService.getMedication(1L);

        Mockito.verify(medicationRepository).findById(Mockito.eq(1L));

        assertEquals(mockMedication.getId(), resultMedication.getId());
        assertEquals(mockMedication.getName(), resultMedication.getName());
        assertEquals(mockMedication.getCode(), resultMedication.getCode());
        assertEquals(mockMedication.getWeight(), resultMedication.getWeight(), 0);
    }

    @Test
    public void testUpdateMedicationNotFound() {
        MedicationRequestDto dto = new MedicationRequestDto();
        dto.setName("Test Medication");
        dto.setCode("TM001");
        dto.setWeight(100.0);

        Mockito.when(medicationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        try {
            medicationService.updateMedication(1L, dto);
            fail("Expected RecordNotFoundException to be thrown");
        } catch (RecordNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testUpdateMedication() {
        Medication mockMedication = new Medication();
        mockMedication.setId(1L);
        mockMedication.setName("Test Medication");
        mockMedication.setCode("TM001");
        mockMedication.setWeight(100.0);

        MedicationRequestDto dto = new MedicationRequestDto();
        dto.setName("Updated Medication");
        dto.setCode("TM002");
        dto.setWeight(150.0);

        Mockito.when(medicationRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(mockMedication));
        Mockito.when(medicationRepository.save(Mockito.any())).thenReturn(mockMedication);

        Medication resultMedication = medicationService.updateMedication(1L, dto);

        Mockito.verify(medicationValidationService).validateName(dto.getName());
        Mockito.verify(medicationValidationService).validateCode(dto.getCode());

        assertEquals(mockMedication.getId(), resultMedication.getId());
        assertEquals(dto.getName(), resultMedication.getName());
        assertEquals(dto.getCode().toUpperCase(Locale.ROOT), resultMedication.getCode());
        assertEquals(dto.getWeight(), resultMedication.getWeight(), 0);
    }



    @Test
    public void testUploadImageFailed() throws IOException {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.getBytes()).thenThrow(new IOException());

        Mockito.when(medicationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception exception = null;
        try {
            medicationService.uploadImage(1L, multipartFile);
        } catch (Exception e) {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof MedicationImageUploadException);
    }


    @Test
    public void testUploadImage() throws IOException {
        Medication mockMedication = new Medication();
        mockMedication.setId(1L);
        mockMedication.setName("Test Medication");
        mockMedication.setCode("TM001");
        mockMedication.setWeight(100.0);

        byte[] mockImageData = new byte[1];

        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.getBytes()).thenReturn(mockImageData);

        Mockito.when(medicationRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(mockMedication));
        Mockito.when(medicationRepository.save(Mockito.any())).thenReturn(mockMedication);

        Medication resultMedication = medicationService.uploadImage(1L, multipartFile);

        Mockito.verify(medicationRepository).save(Mockito.any());

        assertEquals(mockMedication.getId(), resultMedication.getId());
        assertArrayEquals(mockImageData, resultMedication.getImage());
    }

}

