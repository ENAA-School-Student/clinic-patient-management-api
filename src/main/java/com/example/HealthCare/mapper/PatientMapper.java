package com.example.HealthCare.mapper;


import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
    List<PatientDTO> toDTOList(List<Patient> patients);
    List<Patient> toEntityList(List<PatientDTO> patientDTOs);
}
