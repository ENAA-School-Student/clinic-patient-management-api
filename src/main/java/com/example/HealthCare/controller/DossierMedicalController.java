package com.example.HealthCare.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {

    @GetMapping
    public String listerDossierMedicals(){
        return "Medecin RendezVous";
    }

    @PostMapping
    public String creerDossierMedical(){
        return "Medecin RendezVous";
    }

    @DeleteMapping("/{id}")
    public String supprimerDossierMedical() {
        return "Medecin a RendezVous";
    }
    @PutMapping("/{id}")
    public String modifierDossierMedical(){
        return "Medecin a RendezVous";
    }
}
