package com.example.HealthCare.service;


import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.Medecin;
import com.example.HealthCare.repository.MedecinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    public MedecinService(MedecinMapper medecinMapper , MedecinRepository medecinRepository){
        this.medecinMapper = medecinMapper;
        this.medecinRepository = medecinRepository;
    }

    public List<MedecinDTO> lister(){
        List<Medecin> medecinList = medecinRepository.findAll();
        return medecinMapper.toDTOList(medecinList);
    }

    public MedecinDTO ajouter(MedecinDTO medecinDTO){
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        Medecin m = medecinRepository.save(medecin);
        return medecinMapper.toDTO(m);
    }
}
