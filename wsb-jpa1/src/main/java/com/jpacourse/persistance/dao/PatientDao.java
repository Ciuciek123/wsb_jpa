package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentPackage;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    public List<PatientEntity> findByLastName(String lastName);
    public List<VisitEntity> findVisits(Long id);
    public List<PatientEntity> findPatientsWithMoreVisits(int moreThan);
    public List<PatientEntity> findPatientsWithTreatmentPackageAbove(TreatmentPackage selected);
}
