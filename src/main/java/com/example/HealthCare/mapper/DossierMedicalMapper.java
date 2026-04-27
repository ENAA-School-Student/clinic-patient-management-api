package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.model.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
@Mapping(source = "patient.id",target = "patientId")
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);
    @Mapping(target = "id" , ignore = true)
    @Mapping(source = "patientId",target = "patient.id")
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    List<DossierMedicalDTO> toDTOList(List<DossierMedical> dossierMedicals);
}
