package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentPackage;
import com.jpacourse.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        patientService = new PatientServiceImpl(patientDao);
    }


    @Test
    public void shouldDeletePatientAndCascadeVisitsButNotDoctors() {
        // given
        Long patientId = 1L;
        Long doctorId = patientDao.findOne(patientId).getVisits().stream().findFirst().get().getDoctor().getId();
        Long visitId = patientDao.findOne(patientId).getVisits().stream().findFirst().get().getId();

        // when
        patientService.deletePatient(patientId);

        // then
        assertNull(patientDao.findOne(patientId));
        // jak się doda DAO do doktora i wizyt to można sprawdzić czy się nie usunęły itd
    }

    @Test
    public void shouldReturnPatientTOWithCorrectTreatmentPackage() {
        // given
        Long patientId = 1L;

        // when
        PatientTO patientTO = patientService.findById(patientId);

        // then
        assertNotNull(patientTO);
        assertEquals("FREE", patientTO.getTreatmentPackage().toString());
    }
}
