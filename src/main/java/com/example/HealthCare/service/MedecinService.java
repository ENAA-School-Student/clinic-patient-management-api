package com.example.HealthCare.service;


import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.Medecin;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;


    public MedecinService(MedecinMapper medecinMapper , MedecinRepository medecinRepository, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder){
        this.medecinMapper = medecinMapper;
        this.medecinRepository = medecinRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Page<MedecinDTO> lister(Pageable pageable){
        Page<Medecin> medecinList = medecinRepository.findAll(pageable);
        return medecinList.map(medecinMapper::toDTO);
    }

    public Page<MedecinDTO> rechercherParSpecialite(String specialite, Pageable pageable){
        Page<Medecin> medecinList = medecinRepository.findBySpecialiteContainingIgnoreCase(specialite, pageable);
        return medecinList.map(medecinMapper::toDTO);
    }

    public MedecinDTO ajouter(MedecinDTO medecinDTO){
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        medecin.setRole(User.Role.MEDECIN);
        medecin.setPassword(passwordEncoder.encode(medecin.getPassword()));
        Medecin m = medecinRepository.save(medecin);
        return medecinMapper.toDTO(m);
    }

    public MedecinDTO modifier(Long id , MedecinDTO medecinDTO){
        Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("mededecin pas trouver"));

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !medecin.getUsername().equals(currentUsername)) {
                     throw new RuntimeException("Accès refusé : Vous ne pouvez modifier que votre propre profil");
        }

        medecin.setNom(medecinDTO.getNom());
        medecin.setSpecialite(medecinDTO.getSpecialite());
        medecin.setTelephone(medecinDTO.getTelephone());
        medecin.setEmail(medecinDTO.getEmail());
        Medecin m = medecinRepository.save(medecin);
        return medecinMapper.toDTO(m);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void supprimer(Long id){
        medecinRepository.deleteById(id);
    }


}
