package com.example.HealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;

    private Long id;

    private String username;

    private String email;

    private String role;
}