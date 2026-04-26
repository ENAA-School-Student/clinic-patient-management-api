package com.example.HealthCare.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medecin")
@Getter
@Setter
public class Medecin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
 private Long id;
 private String nom;
 private String spécialité;
 private String email;
 private String téléphone;

}
