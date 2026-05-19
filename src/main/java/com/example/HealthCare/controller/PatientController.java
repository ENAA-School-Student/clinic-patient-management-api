package com.example.HealthCare.controller;


import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    public PatientController( PatientService patientService){
        this.patientService = patientService ;
    }

    @GetMapping
    public List<PatientDTO> listerPatients(){
        return patientService.lister();
    }

    @PostMapping
    public PatientDTO ajouterPatients(@Valid @RequestBody PatientDTO patientDTO){
        return patientService.ajouter(patientDTO);
    }


    @PutMapping("/{id}")
    public PatientDTO modifierPatient(@PathVariable Long id ,@Valid @RequestBody PatientDTO patientDTO){
        return patientService.modifier(id , patientDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable Long id) {
        patientService.supprimer(id);
    }

    @GetMapping("/{id}/consulter")
    public PatientDTO cosulterPatient(@PathVariable Long id){
       return patientService.consulter(id);
    }
}
