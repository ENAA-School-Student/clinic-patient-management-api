package com.example.HealthCare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
@Getter
@Setter
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDateTime dateRendezVous;
   private String statut;
   @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
   @ManyToOne
   @JoinColumn(name = "medecin_id")
   private Medecin medecin;
}
