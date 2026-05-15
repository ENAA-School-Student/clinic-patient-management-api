package com.example.HealthCare.repository;

import com.example.HealthCare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {


    @Query("select p from Patient p join p.rendezVousList rv where rv.medecin.id=: medecinId")
    List<Patient> getPatientsByMedecin(Long medecinId);
}
