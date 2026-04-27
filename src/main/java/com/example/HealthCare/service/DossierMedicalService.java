package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.repository.DossierMedicalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;

    public DossierMedicalService(DossierMedicalRepository dossierMedicalRepository , DossierMedicalMapper dossierMedicalMapper ){
        this.dossierMedicalMapper = dossierMedicalMapper;
        this.dossierMedicalRepository = dossierMedicalRepository;
    }

    public List<DossierMedicalDTO> lister(){
        List<DossierMedical> dossierMedicalList = dossierMedicalRepository.findAll();
        return  dossierMedicalMapper.toDTOList(dossierMedicalList);
    }

    public DossierMedicalDTO ajouter(DossierMedicalDTO dossierMedicalDTO){
       DossierMedical dossierMedical = dossierMedicalMapper.toEntity(dossierMedicalDTO);
       DossierMedical d = dossierMedicalRepository.save(dossierMedical);
       return dossierMedicalMapper.toDTO(d);
    }
}
