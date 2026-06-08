package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.repository.DossierMedicalRepository;
import com.example.HealthCare.repository.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;

    public DossierMedicalService(DossierMedicalRepository dossierMedicalRepository , DossierMedicalMapper dossierMedicalMapper,
                                 PatientRepository patientRepository ){
        this.dossierMedicalMapper = dossierMedicalMapper;
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.patientRepository = patientRepository;
    }

    @Cacheable(value = "dossiermedical", key = "#pageable.pageNumber")
    public Page<DossierMedicalDTO> lister(Pageable pageable){
        Page<DossierMedical> dossierMedicalList = dossierMedicalRepository.findAll(pageable);
        return  dossierMedicalList.map(dossierMedicalMapper::toDTO);
    }

    @CacheEvict(value = "dossiermedical", allEntries = true)
    public DossierMedicalDTO creer(DossierMedicalDTO dossierMedicalDTO){
       DossierMedical dossierMedical = dossierMedicalMapper.toEntity(dossierMedicalDTO);
       dossierMedical.setDateCreation(LocalDateTime.now());
       DossierMedical d = dossierMedicalRepository.save(dossierMedical);
       return dossierMedicalMapper.toDTO(d);
    }

    @CacheEvict(value = "dossiermedical", allEntries = true)
    public DossierMedicalDTO modifier(Long id , DossierMedicalDTO dossierMedicalDTO){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));
        dossierMedical.setObservations(dossierMedicalDTO.getObservations());
        dossierMedical.setDiagnostic(dossierMedicalDTO.getDiagnostic());
        dossierMedical.setDateCreation(dossierMedicalDTO.getDateCreation());
        DossierMedical d = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(d);
    }

    public void supprimer(Long id){
        dossierMedicalRepository.deleteById(id);
    }

    public DossierMedicalDTO consulter(Long id){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdminOrMedecin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MEDECIN"));

        if (!isAdminOrMedecin && !dossierMedical.getPatient().getUsername().equals(currentUsername)) {
                     throw new RuntimeException("Accès refusé : Ce dossier ne vous appartient pas");
                }

        return dossierMedicalMapper.toDTO(dossierMedical);
    }

    public DossierMedicalDTO ajouterOBS(Long id , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));

        boolean isAdminOrMedecin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MEDECIN"));

        if (!isAdminOrMedecin) {
            throw new RuntimeException("Accès refusé : Vous devez être ADMIN ou MEDECIN");
        }
        dossierMedical.setObservations(dossierMedical.getObservations() + "\n"+ observation);
        DossierMedical d = dossierMedicalRepository.save(dossierMedical);
        return  dossierMedicalMapper.toDTO(d);
    }

    public DossierMedicalDTO ajouterDiag(Long id , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));
        
        boolean isAdminOrMedecin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_MEDECIN"));

        if (!isAdminOrMedecin) {
            throw new RuntimeException("Accès refusé : Vous devez être ADMIN ou MEDECIN");
        }
        dossierMedical.setDiagnostic(dossierMedical.getDiagnostic() + "\n" + diagnostic);
        DossierMedical d = dossierMedicalRepository.save(dossierMedical);
        return  dossierMedicalMapper.toDTO(d);
    }


}
