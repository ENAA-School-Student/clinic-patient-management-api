package com.example.HealthCare;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.dto.MedecinDTO;
import com.example.HealthCare.dto.PatientDTO;
import com.example.HealthCare.dto.RendezVousDTO;
import com.example.HealthCare.service.DossierMedicalService;
import com.example.HealthCare.service.MedecinService;
import com.example.HealthCare.service.PatientService;
import com.example.HealthCare.service.RendezVousService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@WithMockUser(username = "admin", roles = "ADMIN")
class HealthCareIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private RendezVousService rendezVousService;

    @Autowired
    private DossierMedicalService dossierMedicalService;

    private PatientDTO buildPatientDTO(String suffix) {
        PatientDTO dto = new PatientDTO();
        dto.setNom("Test");
        dto.setPrenom("User");
        dto.setEmail("patient-" + suffix + "@example.com");
        dto.setUsername("patient-" + suffix);
        dto.setPassword("password123");
        dto.setTelephone("0612345678");
        dto.setDateNaissance(LocalDate.now().minusYears(25));
        return dto;
    }

    @Test
    void listerPatientsReturnsSeedData() {
        Page<PatientDTO> page = patientService.lister(PageRequest.of(0, 10));
        assertNotNull(page);
        assertTrue(page.getTotalElements() >= 2);
    }

    @Test
    void listerMedecinsReturnsSeedData() {
        Page<MedecinDTO> page = medecinService.lister(PageRequest.of(0, 10));
        assertNotNull(page);
        assertTrue(page.getTotalElements() >= 2);
    }

    @Test
    void listerRendezVousReturnsSeedData() {
        Page<RendezVousDTO> page = rendezVousService.lister(PageRequest.of(0, 10));
        assertNotNull(page);
        assertTrue(page.getTotalElements() >= 2);
    }

    @Test
    void rechercherPatientParNomWorks() {
        Page<PatientDTO> page = patientService.rechercherParNom("Dupont", PageRequest.of(0, 10));
        assertTrue(page.getTotalElements() >= 1);
        assertTrue(page.getContent().stream().anyMatch(p -> p.getNom().equals("Dupont")));
    }

    @Test
    void rechercherMedecinParSpecialiteWorks() {
        Page<MedecinDTO> page = medecinService.rechercherParSpecialite("Cardiologue", PageRequest.of(0, 10));
        assertTrue(page.getTotalElements() >= 1);
    }

    @Test
    void rechercherRendezVousParStatutWorks() {
        Page<RendezVousDTO> page = rendezVousService.rechercherParStatut("CONFIRME", PageRequest.of(0, 10));
        assertTrue(page.getTotalElements() >= 1);
    }

    @Test
    void ajouterPatientEncodesPasswordAndAssignsRole() {
        PatientDTO saved = patientService.ajouter(buildPatientDTO("ajouter"));

        assertNotNull(saved.getId());
        assertEquals("PATIENT", saved.getRole());
        assertNotEquals("password123", saved.getPassword());
    }

    @Test
    void modifierPatientAsAdminWorks() {
        PatientDTO first = patientService.lister(PageRequest.of(0, 10)).getContent().get(0);
        first.setTelephone("0999999999");

        PatientDTO updated = patientService.modifier(first.getId(), first);

        assertEquals("0999999999", updated.getTelephone());
    }

    @Test
    void consulterPatientAsAdminWorks() {
        PatientDTO first = patientService.lister(PageRequest.of(0, 10)).getContent().get(0);
        PatientDTO consulted = patientService.consulter(first.getId());

        assertNotNull(consulted);
        assertEquals(first.getUsername(), consulted.getUsername());
    }

    @Test
    @WithMockUser(username = "jean.dupont", roles = "PATIENT")
    void myProfileReturnsOwnProfile() {
        PatientDTO profile = patientService.myProfile();
        assertEquals("jean.dupont", profile.getUsername());
    }

    @Test
    void medecinConsulterAsAdminWorks() {
        MedecinDTO first = medecinService.lister(PageRequest.of(0, 10)).getContent().get(0);
        MedecinDTO consulted = medecinService.consulter(first.getId());

        assertNotNull(consulted);
        assertEquals(first.getUsername(), consulted.getUsername());
    }

    @Test
    void modifierMedecinAsAdminWorks() {
        MedecinDTO first = medecinService.lister(PageRequest.of(0, 10)).getContent().get(0);
        first.setTelephone("0777777777");

        MedecinDTO updated = medecinService.modifier(first.getId(), first);

        assertEquals("0777777777", updated.getTelephone());
    }

    @Test
    @WithMockUser(username = "jean.dupont", roles = "PATIENT")
    void mesRendezVousAsPatientReturnsAppointments() {
        List<RendezVousDTO> appointments = rendezVousService.mesRendezVous();
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    void annulerRendezVousWorks() {
        RendezVousDTO first = rendezVousService.lister(PageRequest.of(0, 10)).getContent().get(0);
        RendezVousDTO cancelled = rendezVousService.annuler(first.getId());

        assertEquals("ANNULE", cancelled.getStatut());
    }

    @Test
    @WithMockUser(username = "jean.dupont", roles = "PATIENT")
    void monDossierReturnsDossier() {
        DossierMedicalDTO dossier = dossierMedicalService.monDossier();
        assertNotNull(dossier);
        assertNotNull(dossier.getDateCreation());
    }

    @Test
    void getPatientsByMedecinWorks() {
        MedecinDTO cardiologue = medecinService
                .rechercherParSpecialite("Cardiologue", PageRequest.of(0, 10))
                .getContent().get(0);

        List<PatientDTO> patients = patientService.getPatientsByMedecin(cardiologue.getId());
        assertNotNull(patients);
        assertFalse(patients.isEmpty());
    }
}
