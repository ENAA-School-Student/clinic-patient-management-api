package com.example.HealthCare.controller;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RendezVous")
public class RendezVousController {

    private final RendezVousService rendezVousService;
    public RendezVousController(RendezVousService rendezVousService){
        this.rendezVousService = rendezVousService;
    }

    @GetMapping
    public List<RendezVousDTO> listeRendezVous(){
        return rendezVousService.lister();
    }

    @PostMapping
    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO){
        return rendezVousService.ajouter(rendezVousDTO);
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
