package com.example.HealthCare.controller;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.RendezVousService;
import jakarta.websocket.server.PathParam;
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


    @PutMapping("/annuler")
    public RendezVousDTO annulerRendezVous(@PathParam("id") Long id ){
    return rendezVousService.annuler(id);
    }

    @GetMapping("/{id}/rechercherParPatient")
    public RendezVousDTO rechercherParPatient(@PathVariable Long id){
        return rendezVousService.rechercherParPatientId(id);
    }

    @GetMapping("/{id}/rechercherParMedecin")
    public RendezVousDTO rechercherParMedecin(@PathVariable Long id){
        return rendezVousService.rechercherParMedecinId(id);
    }
}
