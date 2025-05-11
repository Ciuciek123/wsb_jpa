package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentPackage;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback
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

    @Test
    public void shouldFindPatientsByLastName() {

        // when
        List<PatientEntity> result = patientDao.findByLastName("Kowalski");

        // then
        assertFalse(result.isEmpty());
        assertEquals("Kowalski", result.get(0).getLastName());
    }

    @Test
    public void shouldFindVisitsByPatientId() {
        // given

        // when
        List<VisitEntity> result = patientDao.findVisits(1L);

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void shouldFindPatientsWithMoreThanOneVisit() {
        // given

        // when
        List<PatientEntity> result = patientDao.findPatientsWithMoreVisits(1);

        // then
        assertEquals(1, result.size());

    }

    @Test
    public void shouldFindPatientsWithTreatmentPackageAbove() {
        // given

        // when
        List<PatientEntity> result = patientDao.findPatientsWithTreatmentPackageAbove(TreatmentPackage.FREE);

        // then
        assertEquals(5, result.size());

    }

}
