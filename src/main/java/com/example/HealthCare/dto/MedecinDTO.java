package com.example.HealthCare.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MedecinDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "specialite est obligatoire")
    private String specialite;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;


    private String username;


    private String password;

    private String role;

    @NotBlank(message = "Le telephone est obligatoire")
    private String telephone;
}
