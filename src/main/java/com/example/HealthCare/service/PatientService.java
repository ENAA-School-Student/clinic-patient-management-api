package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository , PatientMapper patientMapper, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder){
        this.patientRepository = patientRepository ;
        this.patientMapper = patientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Cacheable(value = "patients", key = "#pageable.pageNumber")
    public Page<PatientDTO> lister(Pageable pageable){
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(patientMapper::toDTO);
    }

    public Page<PatientDTO> rechercherParNom(String nom, Pageable pageable){
        Page<Patient> patients = patientRepository.findByNomContainingIgnoreCase(nom, pageable);
        return patients.map(patientMapper::toDTO);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public PatientDTO ajouter(PatientDTO patientDTO){
        Patient patient =  patientMapper.toEntity(patientDTO);
        patient.setRole(User.Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        Patient p = patientRepository.save(patient);

        return patientMapper.toDTO(p);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public PatientDTO modifier(Long id , PatientDTO patientDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("patient pas trouver"));

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                 .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !patient.getUsername().equals(currentUsername)) {
            throw new RuntimeException("Accès refusé : Vous ne pouvez modifier que votre propre profil");
               }

        patient.setNom(patientDTO.getNom());
        patient.setPrenom(patientDTO.getPrenom());
        patient.setTelephone(patientDTO.getTelephone());
        patient.setDateNaissance(patientDTO.getDateNaissance());
        Patient p = patientRepository.save(patient);
        return patientMapper.toDTO(p);

    }

    public void supprimer(Long id){
        patientRepository.deleteById(id);
    }

    public PatientDTO consulter(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("patient pas trouver"));
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdminOrMedecin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MEDECIN"));

        if (!isAdminOrMedecin && !patient.getUsername().equals(currentUsername)) {
                    throw new RuntimeException("Accès refusé : Vous ne pouvez consulter que votre propre profil");
                }
        return patientMapper.toDTO(patient);
    }



}

