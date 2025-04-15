package com.jpacourse.dto;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentPackage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PatientTO implements Serializable
{
    private Long id;

    private String firstName;

    private String lastName;

    private String telephoneNumber;

    private String email;

    private String patientNumber;

    private LocalDate dateOfBirth;

    private AddressEntity address;

    private TreatmentPackage treatmentPackage;

    private List<VisitEntity> visits = new ArrayList<VisitEntity>();

    private List<VisitEntity> finishedVisits = new ArrayList<VisitEntity>();

    public void setFinishedVisits(List<VisitEntity> visits){
        for(VisitEntity visit:visits){
            if(visit.getTime().isBefore(LocalDateTime.now())){
                this.finishedVisits.add(visit);
            }
        }
    }
}
