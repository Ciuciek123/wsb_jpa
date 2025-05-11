package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentPackage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient or doctor not found");
        }

        VisitEntity newVisit = new VisitEntity();
        newVisit.setTime(visitDate);
        newVisit.setDescription(description);
        newVisit.setDoctor(doctor);
        newVisit.setPatient(patient);

        patient.getVisits().add(newVisit);

        entityManager.merge(patient);


    }

    public List<PatientEntity> findByLastName(String lastName){


        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :lastNameParam", PatientEntity.class)
                .setParameter("lastNameParam", lastName)
                .getResultList();


    }

    public List<VisitEntity> findVisits(Long id){
        return entityManager.createQuery("SELECT p.visits FROM PatientEntity p WHERE p.id = :idParam", VisitEntity.class)
                .setParameter("idParam", id)
                .getResultList();
    }

    public List<PatientEntity> findPatientsWithMoreVisits(int moreThan){
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p JOIN p.visits v GROUP BY p HAVING COUNT(v) > :minVisits", PatientEntity.class)
                .setParameter("minVisits", moreThan)
                .getResultList();
    }

    public List<PatientEntity> findPatientsWithTreatmentPackageAbove(TreatmentPackage selected){

        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.treatmentPackage > :selected", PatientEntity.class)
                .setParameter("selected", selected)
                .getResultList();

    }

}
