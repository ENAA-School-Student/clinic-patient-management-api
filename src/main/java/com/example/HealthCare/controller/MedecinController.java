package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.service.MedecinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medcin")
public class MedecinController {

    private final MedecinService medecinService;
    public MedecinController(MedecinService medecinService){
        this.medecinService = medecinService;
    }

    @GetMapping
    public List<MedecinDTO> listerMedecin(){
        return medecinService.lister();
    }

    @PostMapping
    public MedecinDTO ajouterMedecin(MedecinDTO medecinDTO){
        return medecinService.ajouter(medecinDTO);
    }

    @DeleteMapping("/{id}")
    public String supprimerMedecin() {
        return "Medecin a supprimer";
    }
    @PutMapping("/{id}")
    public String modifierMedecin(){
        return "Medecin a modifier";
    }
}
