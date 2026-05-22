package com.example.HealthCare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("PATIENT")
public class Patient extends User{

    private String nom;
    private String prenom;
    private String telephone;
    private LocalDate dateNaissance;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVousList;
    @OneToOne(mappedBy = "patient" , cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;
}
