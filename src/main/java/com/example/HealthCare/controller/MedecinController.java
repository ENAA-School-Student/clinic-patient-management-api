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
    public MedecinDTO ajouterMedecin(@RequestBody MedecinDTO medecinDTO){
        return medecinService.ajouter(medecinDTO);
    }

    @PutMapping("/{id}")
    public MedecinDTO modifierMedecin(@PathVariable Long id , @RequestBody MedecinDTO medecinDTO){
        return medecinService.modifier(id , medecinDTO);
    }


    @DeleteMapping("/{id}")
    public void supprimerMedecin(@PathVariable Long id) {
        medecinService.supprimer(id);
    }
}
