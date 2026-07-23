package com.example.HealthCare.controller;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.PdfGeneratorService;
import com.example.HealthCare.service.RendezVousService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
@RequestMapping("/RendezVous")
public class RendezVousController {
    @Autowired
     private PdfGeneratorService pdfGeneratorService;

    private final RendezVousService rendezVousService;
    public RendezVousController(RendezVousService rendezVousService){
        this.rendezVousService = rendezVousService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping
    public Page<RendezVousDTO> listeRendezVous(Pageable pageable){
        return rendezVousService.lister(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/search")
    public Page<RendezVousDTO> rechercherParStatut(@RequestParam String statut, Pageable pageable){
        return rendezVousService.rechercherParStatut(statut, pageable);
    }


    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @PostMapping
    public RendezVousDTO creerRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.ajouter(rendezVousDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @PutMapping("/{id}")
    public RendezVousDTO modifierRendezVous(@PathVariable Long id ,@Valid @RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.modifier(id , rendezVousDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @PutMapping("/{id}/annuler")
    public RendezVousDTO annulerRendezVous(@PathVariable Long id ){
    return rendezVousService.annuler(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @GetMapping("/{id}/rechercherParPatient")
    public RendezVousDTO rechercherParPatient(@PathVariable Long id){
        return rendezVousService.rechercherParPatientId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @GetMapping("/{id}/rechercherParMedecin")
    public RendezVousDTO rechercherParMedecin(@PathVariable Long id){
        return rendezVousService.rechercherParMedecinId(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/mine")
    public List<RendezVousDTO> mesRendezVous() {
        return rendezVousService.mesRendezVous();
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/download/mine")
    public ResponseEntity<InputStreamResource> downloadMesRendezVous() {
       List<RendezVousDTO> list = rendezVousService.mesRendezVous();
        ByteArrayInputStream bis = pdfGeneratorService.generateRendezVousPdf(list);

             var headers = new HttpHeaders();
             headers.add("Content-Disposition", "attachment; filename=mes_rendezvous.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                   .body(new InputStreamResource(bis));
    }

}
