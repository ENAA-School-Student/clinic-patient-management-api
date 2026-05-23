package com.example.HealthCare.repository;

import com.example.HealthCare.model.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin , Long> {
    java.util.Optional<Medecin> findByUsername(String username);
    Page<Medecin> findBySpecialiteContainingIgnoreCase(String specialite, Pageable pageable);
}
