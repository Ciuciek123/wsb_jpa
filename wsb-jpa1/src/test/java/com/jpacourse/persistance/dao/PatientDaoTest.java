package com.jpacourse.persistance.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    public void shouldAddVisitToPatient() {
        // given
        Long patientId = 1L;
        Long doctorId = 2L;
        LocalDateTime visitDate = LocalDateTime.of(2025, 4, 14, 12, 0);
        String description = "Consultation";
        int oldSize = patientDao.findOne(patientId).getVisits().size();

        // when
        patientDao.addVisitToPatient(patientId, doctorId, visitDate, description);

        // then
        assertEquals(oldSize+1, patientDao.findOne(patientId).getVisits().size());
    }
}
