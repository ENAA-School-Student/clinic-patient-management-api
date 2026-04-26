package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientDTO {

    private Long id;
    private String nom;
    private String prénom;
    private String email;
    private String téléphone;
    private LocalDateTime dateNaissance;
}
