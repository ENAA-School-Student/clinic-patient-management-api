package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RendezVousDTO {

    private Long id;
    private LocalDateTime dateRendezVous;
    private String statut;
}
