package com.example.HealthCare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class RendezVousDTO {
    private Long id;

    @NotBlank(message = "date est obligatoire")
    private LocalDateTime dateRendezVous;

    @NotBlank(message = "statut est obligatoire")
    private String statut;
    private Long patientId;
    private Long medecinId;
}
