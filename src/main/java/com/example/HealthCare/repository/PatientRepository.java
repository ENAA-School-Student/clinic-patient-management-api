package com.example.HealthCare.repository;

import com.example.HealthCare.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {


    @Query("select p from Patient p join p.rendezVousList rv where rv.medecin.id=:medecinId")
    List<Patient> getPatientsByMedecin(Long medecinId);

    Optional<Patient> findByUsername(String username);

    Page<Patient> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    @EntityGraph(attributePaths = {"rendezVousList"})
     Optional<Patient> findWithRendezVousById(Long id);

    @Query("""
       SELECT DISTINCT p
       FROM Patient p
       JOIN p.rendezVousList rv
       WHERE rv.medecin.id = :medecinId
       """)
    List<Patient> getPatientsByMedecin(Long medecinId);

}
