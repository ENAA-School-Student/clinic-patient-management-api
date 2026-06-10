package com.example.HealthCare.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("MEDECIN")
public class Medecin extends User {
 private String nom;
 private String specialite;
 private String telephone;
 @OneToMany(mappedBy="medecin" , cascade = CascadeType.ALL)
 private List<RendezVous> rendezVousList;

}
