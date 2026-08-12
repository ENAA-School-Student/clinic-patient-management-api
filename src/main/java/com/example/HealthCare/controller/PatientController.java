package com.example.HealthCare.controller;
import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController( PatientService patientService){
        this.patientService = patientService ;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<PatientDTO> listerPatients(Pageable pageable){
        return patientService.lister(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public Page<PatientDTO> rechercherParNom(@RequestParam String nom, Pageable pageable){
        return patientService.rechercherParNom(nom, pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public PatientDTO ajouterPatients(@Valid @RequestBody PatientDTO patientDTO){
        return patientService.ajouter(patientDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @PutMapping("/{id}")
    public PatientDTO modifierPatient(@PathVariable Long id ,@Valid @RequestBody PatientDTO patientDTO){
        return patientService.modifier(id , patientDTO);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable Long id) {
        patientService.supprimer(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/{id}/consulter")
    public PatientDTO consulterPatient(@PathVariable Long id){
       return patientService.consulter(id);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/me")
    public PatientDTO myProfile() {
        return patientService.myProfile();
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PutMapping("/me")
    public PatientDTO updateMyProfile(@Valid @RequestBody PatientDTO patientDTO) {
        return patientService.updateMyProfile(patientDTO);
    }

}
