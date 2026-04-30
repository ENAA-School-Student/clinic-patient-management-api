package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.service.DossierMedicalService;
import jakarta.websocket.server.PathParam;
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
    public DossierMedicalDTO creerDossierMedical(@RequestBody DossierMedicalDTO dossierMedicalDTO){
        return dossierMedicalService.creer(dossierMedicalDTO) ;
    }


    @PutMapping("/{id}")
    public DossierMedicalDTO modifierDossierMedical(@PathVariable Long id , @RequestBody DossierMedicalDTO dossierMedicalDTO){
        return dossierMedicalService.modifier(id , dossierMedicalDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerDossierMedical(@PathVariable Long id) {
        dossierMedicalService.supprimer(id);
    }

    @GetMapping("/{id}/consulter")
    public DossierMedicalDTO consulterDossierMedical(@PathVariable Long id){
        return dossierMedicalService.consulter(id);
    }

    @PutMapping("/{id}/ajouterObservation")
    public DossierMedicalDTO ajouterObservation(Long id , @RequestBody String observation){
        return  dossierMedicalService.ajouterOBS(id , observation);
    }

    @PutMapping("/{id}/ajouterDiagnostic")
    public DossierMedicalDTO ajouterDiagnostic(Long id , @RequestBody String diagnostic){
        return  dossierMedicalService.ajouterDiag(id , diagnostic);
    }
}


