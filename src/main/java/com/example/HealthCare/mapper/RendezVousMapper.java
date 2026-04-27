package com.example.HealthCare.mapper;


import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.model.RendezVous;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {

    RendezVousDTO toDTO(RendezVous rendezVous);
    RendezVous toEntity(RendezVousDTO rendezVousDTO);
    List<RendezVousDTO> toDTOList(List<RendezVous> rendezVousList);
    List<RendezVous> toEntityList(List<RendezVousDTO> rendezVousDTOs);
}
