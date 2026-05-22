package com.example.HealthCare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "username est obligatoire")
    private String username;

    @NotBlank(message = "L'email est obligatoire")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6 , max = 40)
    private String password;

    @NotBlank
    private String role;

}
