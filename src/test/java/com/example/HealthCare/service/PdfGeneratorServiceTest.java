package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.dto.RendezVousDTO;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfGeneratorServiceTest {

    private final PdfGeneratorService pdfGeneratorService = new PdfGeneratorService();

    @Test
    void generateDossierPdfProducesValidPdf() throws IOException {
        DossierMedicalDTO dossier = new DossierMedicalDTO();
        dossier.setId(1L);
        dossier.setPatientId(10L);
        dossier.setDiagnostic("Diagnostic de test");
        dossier.setObservations("Observation de test");
        dossier.setDateCreation(LocalDateTime.now());

        ByteArrayInputStream pdf = pdfGeneratorService.generateDossierPdf(dossier);
        assertNotNull(pdf);

        byte[] bytes = pdf.readAllBytes();
        assertTrue(bytes.length > 0, "PDF must not be empty");
        assertArrayEquals(new byte[]{'%', 'P', 'D', 'F'}, java.util.Arrays.copyOf(bytes, 4));
    }

    @Test
    void generateRendezVousPdfProducesValidPdf() throws IOException {
        RendezVousDTO rv = new RendezVousDTO();
        rv.setId(1L);
        rv.setStatut("CONFIRME");
        rv.setDateRendezVous(LocalDateTime.now());

        ByteArrayInputStream pdf = pdfGeneratorService.generateRendezVousPdf(List.of(rv));
        assertNotNull(pdf);

        byte[] bytes = pdf.readAllBytes();
        assertTrue(bytes.length > 0, "PDF must not be empty");
        assertArrayEquals(new byte[]{'%', 'P', 'D', 'F'}, java.util.Arrays.copyOf(bytes, 4));
    }

    @Test
    void generateSimpleReportProducesValidPdf() throws IOException {
        ByteArrayInputStream pdf = pdfGeneratorService.generateSimpleReport("Rapport de test", "Contenu du rapport");
        assertNotNull(pdf);

        byte[] bytes = pdf.readAllBytes();
        assertTrue(bytes.length > 0, "PDF must not be empty");
        assertArrayEquals(new byte[]{'%', 'P', 'D', 'F'}, java.util.Arrays.copyOf(bytes, 4));
    }
}
