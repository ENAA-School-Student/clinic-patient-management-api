package com.example.HealthCare.service;

import com.example.HealthCare.config.JwtUtils;
import com.example.HealthCare.dto.LoginResponseDTO;
import com.example.HealthCare.dto.UserLoginDTO;
import com.example.HealthCare.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    private UserRegisterDTO buildRegisterDTO(String suffix) {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("auth-" + suffix);
        dto.setEmail("auth-" + suffix + "@example.com");
        dto.setPassword("password123");
        dto.setRole("PATIENT");
        return dto;
    }

    @Test
    void registerPatientReturnsValidToken() {
        LoginResponseDTO response = authService.register(buildRegisterDTO("register"));

        assertNotNull(response.getToken());
        assertEquals("auth-register", response.getUsername());
        assertEquals("PATIENT", response.getRole());
        assertTrue(jwtUtils.validateToken(response.getToken()));
    }

    @Test
    void registerThenLoginWorks() {
        authService.register(buildRegisterDTO("login"));

        UserLoginDTO login = new UserLoginDTO();
        login.setUsername("auth-login");
        login.setPassword("password123");

        LoginResponseDTO response = authService.login(login);

        assertNotNull(response.getToken());
        assertEquals("auth-login", response.getUsername());
        assertTrue(jwtUtils.validateToken(response.getToken()));
    }

    @Test
    void registerWithExistingEmailThrows() {
        authService.register(buildRegisterDTO("duplicate"));

        UserRegisterDTO duplicate = buildRegisterDTO("duplicate");
        assertThrows(RuntimeException.class, () -> authService.register(duplicate));
    }
}
