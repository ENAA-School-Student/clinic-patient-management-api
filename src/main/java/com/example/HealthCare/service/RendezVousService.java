package com.example.HealthCare.service;

import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.Medecin;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.RendezVousRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public RendezVousService(RendezVousRepository rendezVousRepository , RendezVousMapper rendezVousMapper,
                             PatientRepository patientRepository, MedecinRepository medecinRepository){
        this.rendezVousMapper = rendezVousMapper;
        this.rendezVousRepository = rendezVousRepository;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    @Cacheable(value = "rendezvous", key = "#pageable.pageNumber")
     public Page<RendezVousDTO> lister(Pageable pageable){
       Page<RendezVous> rendezVousList =  rendezVousRepository.findAll(pageable);
       return rendezVousList.map(rendezVousMapper::toDTO);
     }

     public Page<RendezVousDTO> rechercherParStatut(String statut, Pageable pageable){
        Page<RendezVous> rendezVousList =  rendezVousRepository.findByStatutContainingIgnoreCase(statut, pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
     }

    @CacheEvict(value = "rendezvous", allEntries = true)
     public RendezVousDTO ajouter(RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);
        RendezVous r = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(r);
     }

    @CacheEvict(value = "rendezvous", allEntries = true)
     public RendezVousDTO modifier(Long id , RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(() -> new RuntimeException("rendezVous pas trouver"));
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setStatut(rendezVousDTO.getStatut());
        RendezVous r = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(r);
     }

     public RendezVousDTO annuler(Long id){
       RendezVous rendezVous =  rendezVousRepository.findById(id).orElseThrow(() -> new RuntimeException("rendezvous pas trouver"));
       rendezVous.setStatut("ANNULE");
       RendezVous r = rendezVousRepository.save(rendezVous);
       return rendezVousMapper.toDTO(r);
     }

     public RendezVousDTO rechercherParPatientId(Long patientId){
        RendezVous rendezVous = rendezVousRepository.findByPatientId(patientId);
        return rendezVousMapper.toDTO(rendezVous);
     }
     public RendezVousDTO rechercherParMedecinId(Long medecinId){
        RendezVous rendezVous = rendezVousRepository.findByMedecinId(medecinId);
        return rendezVousMapper.toDTO(rendezVous);
    }

    public List<RendezVousDTO> mesRendezVous(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))){
            Patient p = patientRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("patient pas trouver"));
            return rendezVousMapper.toDTOList(rendezVousRepository.findAllByPatientId(p.getId()));
        }

        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEDECIN"))){
            Medecin m = medecinRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("medecin pas trouver"));
            return rendezVousMapper.toDTOList(rendezVousRepository.findAllByMedecinId(m.getId()));
        }

        return List.of();
    }
}
