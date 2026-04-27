package com.example.HealthCare.service;

import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;

    public RendezVousService(RendezVousRepository rendezVousRepository , RendezVousMapper rendezVousMapper){
        this.rendezVousMapper = rendezVousMapper;
        this.rendezVousRepository = rendezVousRepository;
    }
     public List<RendezVousDTO> lister(){
       List<RendezVous> rendezVousList =  rendezVousRepository.findAll();
       return rendezVousMapper.toDTOList(rendezVousList);
     }

     public RendezVousDTO ajouter(RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);
        RendezVous r = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(r);
     }

     public RendezVousDTO modifier(Long id , RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(() -> new RuntimeException("rendezVous pas trouver"));
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setStatut(rendezVousDTO.getStatut());
        RendezVous r = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(r);
     }

     public void supprimer(Long id){
        rendezVousRepository.deleteById(id);
     }
}
