package com.example.HealthCare.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RendezVous")
public class RendezVousController {

    @GetMapping
    public String listeRendezVous(){
        return "Medecin RendezVous";
    }

    @PostMapping
    public String creerRendezVous(){
        return "Medecin RendezVous";
    }

    @DeleteMapping("/{id}")
    public String supprimerRendezVous() {
        return "Medecin a RendezVous";
    }
    @PutMapping("/{id}")
    public String modifierRendezVous(){
        return "Medecin a RendezVous";
    }
}
