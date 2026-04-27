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
    public RendezVousDTO creerRendezVous(@RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.ajouter(rendezVousDTO);
    }


    @PutMapping("/{id}")
    public RendezVousDTO modifierRendezVous(@PathVariable Long id , @RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.modifier(id , rendezVousDTO);
    }

    @DeleteMapping("/{id}")
    public void supprimerRendezVous(@PathVariable Long id) {
        rendezVousService.supprimer(id);
    }
}
