package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.model.Medecin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {

    MedecinDTO toDTO(Medecin medecin);
    @Mapping(target = "id" , ignore = true)
    Medecin toEntity(MedecinDTO medecinDTO);
    List<MedecinDTO> toDTOList(List<Medecin> medecins);

}
