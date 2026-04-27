package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.model.DossierMedical;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    DossierMedicalDTO toDTO(DossierMedical dossierMedical);
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    List<DossierMedical> toEntityList(List<DossierMedicalDTO> dossierMedicalDTOs);
    List<DossierMedicalDTO> toDTOList(List<DossierMedical> dossierMedicals);
}
