package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.service.DossierMedicalService;
import com.example.HealthCare.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    private final DossierMedicalService dossierMedicalService;

    public DossierMedicalController(DossierMedicalService dossierMedicalService){
        this.dossierMedicalService = dossierMedicalService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<DossierMedicalDTO> listerDossierMedicals(Pageable pageable){
        return dossierMedicalService.lister(pageable);
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

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadDossier(@PathVariable Long id) {
        DossierMedicalDTO dossier = dossierMedicalService.consulter(id);
        ByteArrayInputStream bis = pdfGeneratorService.generateDossierPdf(dossier);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=dossier_medical_" + id + ".pdf");

        return ResponseEntity
                   .ok()
                  .headers(headers)
                  .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/me")
    public DossierMedicalDTO monDossier() {
        return dossierMedicalService.monDossier();
    }

}
