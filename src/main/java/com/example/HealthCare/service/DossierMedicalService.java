package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.repository.DossierMedicalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public DossierMedicalDTO creer(DossierMedicalDTO dossierMedicalDTO){
       DossierMedical dossierMedical = dossierMedicalMapper.toEntity(dossierMedicalDTO);
       dossierMedical.setDateCreation(LocalDateTime.now());
       DossierMedical d = dossierMedicalRepository.save(dossierMedical);
       return dossierMedicalMapper.toDTO(d);
    }

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
        return dossierMedicalMapper.toDTO(dossierMedical);
    }

    public DossierMedicalDTO ajouterOBS(Long id , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));
        dossierMedical.setObservations(dossierMedical.getObservations() + "\n"+ observation);
        DossierMedical d = dossierMedicalRepository.save(dossierMedical);
        return  dossierMedicalMapper.toDTO(d);
    }

    public DossierMedicalDTO ajouterDiag(Long id , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("dossier pas trouver"));
        dossierMedical.setDiagnostic(dossierMedical.getDiagnostic() + "\n" + diagnostic);
        DossierMedical d = dossierMedicalRepository.save(dossierMedical);
        return  dossierMedicalMapper.toDTO(d);
    }
}


