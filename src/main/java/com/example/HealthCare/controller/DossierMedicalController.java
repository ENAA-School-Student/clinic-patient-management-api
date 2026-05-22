package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.service.DossierMedicalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

    public DossierMedicalController(DossierMedicalService dossierMedicalService){
        this.dossierMedicalService = dossierMedicalService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<DossierMedicalDTO> listerDossierMedicals(){
        return dossierMedicalService.lister();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public DossierMedicalDTO creerDossierMedical(@RequestBody DossierMedicalDTO dossierMedicalDTO){
        return dossierMedicalService.creer(dossierMedicalDTO) ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public DossierMedicalDTO modifierDossierMedical(@PathVariable Long id , @RequestBody DossierMedicalDTO dossierMedicalDTO){
        return dossierMedicalService.modifier(id , dossierMedicalDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void supprimerDossierMedical(@PathVariable Long id) {
        dossierMedicalService.supprimer(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/{id}/consulter")
    public DossierMedicalDTO consulterDossierMedical(@PathVariable Long id){
        return dossierMedicalService.consulter(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @PutMapping("/{id}/ajouterObservation")
    public DossierMedicalDTO ajouterObservation(@PathVariable Long id , @RequestBody String observation){
        return  dossierMedicalService.ajouterOBS(id , observation);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @PutMapping("/{id}/ajouterDiagnostic")
    public DossierMedicalDTO ajouterDiagnostic(@PathVariable Long id , @RequestBody String diagnostic){
        return  dossierMedicalService.ajouterDiag(id , diagnostic);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/mine")
    public DossierMedicalDTO monDossier() {
        return dossierMedicalService.monDossier();
    }
}
