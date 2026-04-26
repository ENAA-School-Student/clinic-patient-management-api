package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class DossierMedicalDTO {

    private Long id;
    private String diagnostic;
    private String observations;
    private LocalDateTime dateCreation;
}
