package com.example.HealthCare.repository;

import com.example.HealthCare.model.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DossierMedicalRepository extends JpaRepository<DossierMedical , Long> {
    DossierMedical findByPatientId(Long patientId);
}
