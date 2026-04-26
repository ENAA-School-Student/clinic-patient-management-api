package com.example.HealthCare.repository;

import com.example.HealthCare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {
}
