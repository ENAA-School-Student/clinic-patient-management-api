package com.example.HealthCare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDateTime dateNaissance;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVousList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dossier_medical_id", referencedColumnName = "id")
    private DossierMedical dossierMedical;
}
