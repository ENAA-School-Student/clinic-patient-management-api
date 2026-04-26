package com.example.HealthCare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "rendezVous")
@Getter
@Setter
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDateTime dateRendezVous;
   private String statut;
}
