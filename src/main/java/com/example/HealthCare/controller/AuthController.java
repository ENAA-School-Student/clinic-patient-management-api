package com.example.HealthCare.controller;

import com.example.HealthCare.dto.LoginResponseDTO;
import com.example.HealthCare.dto.UserLoginDTO;
import com.example.HealthCare.dto.UserRegisterDTO;
import com.example.HealthCare.model.User;
import com.example.HealthCare.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired 
    private AuthService authService;

    @PostMapping("/register")
    public LoginResponseDTO register(@Valid @RequestBody UserRegisterDTO userRegister) {
        return authService.register(userRegister);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserLoginDTO userLogin) {
        return authService.login(userLogin);
    }
}
