package com.example.HealthCare.controller;


import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Patients")
public class PatientController {

    @GetMapping
    public String listerPatients(){
        return "patients list";
    }

    @PostMapping
    public String ajouterPatients(){
        return "Patients a ajouter";
    }

    @DeleteMapping("/{id}")
    public String supprimerPatient() {
        return "Patients a supprimer";
    }
    @PutMapping("/{id}")
    public String modifierPatient(){
        return "Patients a modifier";
    }
}
