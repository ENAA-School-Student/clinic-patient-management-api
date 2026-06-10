package com.example.HealthCare.controller;

import com.example.HealthCare.service.PdfGeneratorService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final PdfGeneratorService pdfGeneratorService;

    public ReportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/simple")
    public ResponseEntity<InputStreamResource> downloadSimpleReport() {
        String title = "Rapport d'Activité Simplifié";
        String content = "Ceci est un rapport généré automatiquement par le système HealthCare.\n" +
                "Il contient des informations générales sur l'état du système et les activités récentes.\n" +
                "\n" +
                "Statistiques globales :\n" +
                "- Nombre de patients actifs : 150\n" +
                "- Rendez-vous prévus aujourd'hui : 12\n" +
                "- Nouveaux dossiers créés cette semaine : 5";

        ByteArrayInputStream bis = pdfGeneratorService.generateSimpleReport(title, content);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=rapport_simple.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
