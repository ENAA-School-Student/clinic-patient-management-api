package com.example.HealthCare.mapper;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {
@Mapping(source = "patient.id" , target = "patientId")
@Mapping(source = "medecin.id" , target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);
    @Mapping(target = "id" , ignore = true)
    @Mapping(source = " patientId" , target = "patient.id")
    @Mapping(source = "medecinId" , target = "medecin.id ")
    RendezVous toEntity(RendezVousDTO rendezVousDTO);
    List<RendezVousDTO> toDTOList(List<RendezVous> rendezVousList);
}
