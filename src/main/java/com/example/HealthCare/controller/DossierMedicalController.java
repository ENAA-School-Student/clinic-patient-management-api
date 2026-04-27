package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.service.DossierMedicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

    public DossierMedicalController(DossierMedicalService dossierMedicalService){
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping
    public List<DossierMedicalDTO> listerDossierMedicals(){
        return dossierMedicalService.lister();
    }

    @PostMapping
    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierMedicalDTO){
        return dossierMedicalService.ajouter(dossierMedicalDTO) ;
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
