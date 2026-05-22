package com.example.HealthCare;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.DossierMedicalRepository;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.RendezVousRepository;
import com.example.HealthCare.service.DossierMedicalService;
import com.example.HealthCare.service.PatientService;
import com.example.HealthCare.service.RendezVousService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HealthCareFlowsTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;
    @InjectMocks
    private PatientService patientService;

    @Mock
    private RendezVousRepository rendezVousRepository;
    @Mock
    private RendezVousMapper rendezVousMapper;
    @Mock
    private MedecinRepository medecinRepository;
    @Mock
    private PatientRepository patientRepositoryForRendezVous;
    @InjectMocks
    private RendezVousService rendezVousService;

    @Mock
    private DossierMedicalRepository dossierMedicalRepository;
    @Mock
    private DossierMedicalMapper dossierMedicalMapper;
    @Mock
    private PatientRepository patientRepositoryForDossier;
    @InjectMocks
    private DossierMedicalService dossierMedicalService;

    @Test
    public void testFlow1_PatientRegistration() {
        PatientDTO dto = new PatientDTO();
        dto.setNom("John");
        dto.setPrenom("Doe");

        Patient p = new Patient();
        p.setNom("John");

        when(patientMapper.toEntity(any())).thenReturn(p);
        when(patientRepository.save(any())).thenReturn(p);
        when(patientMapper.toDTO(any())).thenReturn(dto);

        PatientDTO result = patientService.ajouter(dto);
        assertNotNull(result);
    }

    @Test
    public void testFlow2_RendezVousBooking() {
        RendezVousDTO dto = new RendezVousDTO();
        dto.setDateRendezVous(LocalDateTime.now());

        RendezVous r = new RendezVous();
        r.setId(1L);

        when(rendezVousMapper.toEntity(any())).thenReturn(r);
        when(rendezVousRepository.save(any())).thenReturn(r);
        when(rendezVousMapper.toDTO(any())).thenReturn(dto);

        RendezVousDTO result = rendezVousService.ajouter(dto);
        assertNotNull(result);
    }

    @Test
    public void testFlow3_DossierMedicalCreation() {
        DossierMedicalDTO dto = new DossierMedicalDTO();
        dto.setObservations("Initial observation");
        dto.setPatientId(1L);

        DossierMedical dm = new DossierMedical();
        dm.setId(1L);

        when(patientRepositoryForDossier.findById(any())).thenReturn(Optional.of(new Patient()));
        when(dossierMedicalMapper.toEntity(any())).thenReturn(dm);
        when(dossierMedicalRepository.save(any())).thenReturn(dm);
        when(dossierMedicalMapper.toDTO(any())).thenReturn(dto);

        DossierMedicalDTO result = dossierMedicalService.creer(dto);
        assertNotNull(result);
    }
}

