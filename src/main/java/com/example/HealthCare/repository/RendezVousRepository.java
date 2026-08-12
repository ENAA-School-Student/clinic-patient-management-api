package com.example.HealthCare.repository;

import com.example.HealthCare.model.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous , Long> {
    List<RendezVous> findByPatientId(Long patientId);
    List<RendezVous> findByMedecinId(Long medecinId);

    List<RendezVous> findAllByPatientId(Long patientId);
    List<RendezVous> findAllByMedecinId(Long medecinId);

    Page<RendezVous> findByStatutContainingIgnoreCase(String statut, Pageable pageable);
}
