package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.model.Medecin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {

    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
    List<MedecinDTO> toDTOList(List<Medecin> medecins);
    List<Medecin> toEntityList(List<MedecinDTO> medecinDTOs);
}
