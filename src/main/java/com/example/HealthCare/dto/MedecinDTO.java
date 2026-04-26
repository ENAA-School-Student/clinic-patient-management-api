package com.example.HealthCare.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedecinDTO {

    private Long id;
    private String nom;
    private String spécialité;
    private String email;
    private String téléphone;
}
