package com.example.HealthCare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    private String nom;
    private String prénom;
    private String email;
    private String téléphone;
    private LocalDateTime dateNaissance;
}
