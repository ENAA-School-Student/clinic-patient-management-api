package com.example.HealthCare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class RendezVousDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull(message = "date est obligatoire")
    private LocalDateTime dateRendezVous;

    @NotBlank(message = "statut est obligatoire")
    private String statut;
    private Long patientId;
    private Long medecinId;
}
