package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
public class DossierMedicalDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String diagnostic;
    private String observations;
    private LocalDateTime dateCreation;
    private Long patientId;
}
