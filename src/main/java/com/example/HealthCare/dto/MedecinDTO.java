package com.example.HealthCare.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedecinDTO {

    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;
}
