package com.example.HealthCare;

import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.service.PatientService;
import com.example.HealthCare.service.MedecinService;
import com.example.HealthCare.service.RendezVousService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HealthCareIntegrationTest {

    @Autowired private PatientService patientService;
    @Autowired private MedecinService medecinService;
    @Autowired private RendezVousService rendezVousService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminFlows() {

        PatientDTO dto = new PatientDTO();
        dto.setNom("Test");
        dto.setPrenom("User");
        dto.setEmail("test@example.com");
        dto.setUsername("testuser");
        dto.setPassword("password");
        dto.setTelephone("123456");
        dto.setDateNaissance(LocalDate.now().minusYears(20));
        
        PatientDTO saved = patientService.ajouter(dto);
        assertNotNull(saved.getId());


        assertNotNull(patientService.lister(PageRequest.of(0, 10)));
        assertNotNull(medecinService.lister(PageRequest.of(0, 10)));
        assertNotNull(rendezVousService.lister(PageRequest.of(0, 10)));

        assertNotNull(patientService.rechercherParNom("Test", PageRequest.of(0, 10)));
    }
}
