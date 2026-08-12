package com.example.HealthCare.repository;

import com.example.HealthCare.model.DossierMedical;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {

    Optional<DossierMedical> findByPatientId(Long patientId);

    @EntityGraph(attributePaths = "patient")
    DossierMedical findWithPatientById(Long id);

    @EntityGraph(attributePaths = "patient")
    Optional<DossierMedical> findByPatientUsername(String username);

}
