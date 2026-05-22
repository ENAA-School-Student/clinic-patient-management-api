package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecin")
public class MedecinController {

    private final MedecinService medecinService;
    public MedecinController(MedecinService medecinService){
        this.medecinService = medecinService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<MedecinDTO> listerMedecin(){
        return medecinService.lister();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MedecinDTO ajouterMedecin(@Valid @RequestBody MedecinDTO medecinDTO){
        return medecinService.ajouter(medecinDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @PutMapping("/{id}")
    public MedecinDTO modifierMedecin(@PathVariable Long id ,@Valid @RequestBody MedecinDTO medecinDTO){
        return medecinService.modifier(id , medecinDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void supprimerMedecin(@PathVariable Long id) {
        medecinService.supprimer(id);
    }

    @PreAuthorize("hasRole('MEDECIN')")
    @GetMapping("/mine")
    public MedecinDTO monProfil() {
        return medecinService.monProfil();
    }
}
