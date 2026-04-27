package com.example.HealthCare.mapper;


import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
 @Mapping(source = "dossierMedical.id" , target = "dossierMedicalId")
    PatientDTO toDTO(Patient patient);
    @Mapping(target = "id" , ignore = true)
    @Mapping(source = "dossierMedicalId" , target = "dossierMedical.id")
    Patient toEntity(PatientDTO patientDTO);
    List<PatientDTO> toDTOList(List<Patient> patients);

}
