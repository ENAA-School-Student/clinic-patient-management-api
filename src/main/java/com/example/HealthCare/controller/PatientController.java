package com.example.HealthCare.controller;


import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Patients")
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
    public PatientDTO ajouterPatients(PatientDTO patientDTO){
        return patientService.ajouter(patientDTO);
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
