package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalDTO;
import com.example.HealthCare.dto.RendezVousDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateDossierPdf(DossierMedicalDTO dossier) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Dossier Médical n° " + dossier.getId(), fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Espace

            document.add(new Paragraph("Date de création : " + dossier.getDateCreation()));
            document.add(new Paragraph("ID Patient : " + dossier.getPatientId()));
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("Diagnostic :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            document.add(new Paragraph(dossier.getDiagnostic()));
            
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("Observations :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            document.add(new Paragraph(dossier.getObservations()));

            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generateRendezVousPdf(List<RendezVousDTO> rendezvousList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("Liste des Rendez-vous", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Date");
            table.addCell("Statut");

            for (RendezVousDTO rv : rendezvousList) {
                table.addCell(rv.getId().toString());
                table.addCell(rv.getDateRendezVous().toString());
                table.addCell(rv.getStatut());
            }

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generateSimpleReport(String titleText, String contentText) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph(titleText, fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Date du rapport : " + java.time.LocalDateTime.now()));
            document.add(new Paragraph(" "));

            Font fontContent = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph content = new Paragraph(contentText, fontContent);
            document.add(content);

            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
