package com.example.HealthCare.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Medcin")
public class MedecinController {

    @GetMapping
    public String listerMedecin(){
        return "Medecin list";
    }

    @PostMapping
    public String ajouterMedecin(){
        return "Medecin ajouter";
    }

    @DeleteMapping("/{id}")
    public String supprimerMedecin() {
        return "Medecin a supprimer";
    }
    @PutMapping("/{id}")
    public String modifierMedecin(){
        return "Medecin a modifier";
    }
}
