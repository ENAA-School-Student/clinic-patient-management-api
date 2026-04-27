package com.example.HealthCare.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "medecin")
@Getter
@Setter
public class Medecin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
 private Long id;
 private String nom;
 private String specialite;
 private String email;
 private String telephone;
 @OneToMany(mappedBy="medecin" , cascade = CascadeType.ALL)
 private List<RendezVous> rendezVousList;


}
