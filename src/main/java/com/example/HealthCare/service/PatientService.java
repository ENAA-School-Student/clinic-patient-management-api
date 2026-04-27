package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository , PatientMapper patientMapper){
        this.patientRepository = patientRepository ;
        this.patientMapper = patientMapper;
    }

    public List<PatientDTO> lister(){
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toDTOList(patients);
    }

    public PatientDTO ajouter(PatientDTO patientDTO){
        Patient patient =  patientMapper.toEntity(patientDTO);
        Patient p = patientRepository.save(patient);

        return patientMapper.toDTO(p);
    }

}
