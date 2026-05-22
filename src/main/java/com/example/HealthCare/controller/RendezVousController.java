package com.example.HealthCare.controller;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.RendezVousService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RendezVous")
public class RendezVousController {

    private final RendezVousService rendezVousService;
    public RendezVousController(RendezVousService rendezVousService){
        this.rendezVousService = rendezVousService;
    }


    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping
    public List<RendezVousDTO> listeRendezVous(){
        return rendezVousService.lister();
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


    @PreAuthorize("hasAnyRole('PATIENT','MEDECIN')")
    @GetMapping("/mine")
    public List<RendezVousDTO> mesRendezVous() {
        return rendezVousService.mesRendezVous();
    }
}
