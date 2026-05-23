package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<MedecinDTO> listerMedecin(Pageable pageable){
        return medecinService.lister(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public Page<MedecinDTO> rechercherParSpecialite(@RequestParam String specialite, Pageable pageable){
        return medecinService.rechercherParSpecialite(specialite, pageable);
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


}
