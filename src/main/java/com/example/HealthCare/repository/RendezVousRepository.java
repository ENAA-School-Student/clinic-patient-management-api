package com.example.HealthCare.repository;

import com.example.HealthCare.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous , Long> {
    RendezVous findByPatientId(Long patientId);
    RendezVous findByMedecinId(Long medecinId);
}
