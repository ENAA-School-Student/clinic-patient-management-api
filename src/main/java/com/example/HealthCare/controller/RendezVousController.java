package com.example.HealthCare.controller;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.RendezVousService;
import jakarta.validation.Valid;
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
    public RendezVousDTO creerRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.ajouter(rendezVousDTO);
    }


    @PutMapping("/{id}")
    public RendezVousDTO modifierRendezVous(@PathVariable Long id ,@Valid @RequestBody RendezVousDTO rendezVousDTO){
        return rendezVousService.modifier(id , rendezVousDTO);
    }


    @PutMapping("/{id}/annuler")
    public RendezVousDTO annulerRendezVous(@PathVariable Long id ){
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
