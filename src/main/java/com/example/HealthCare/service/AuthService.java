package com.example.HealthCare.service;

import com.example.HealthCare.config.JwtUtils;
import com.example.HealthCare.dto.LoginResponseDTO;
import com.example.HealthCare.dto.UserLoginDTO;
import com.example.HealthCare.dto.UserRegisterDTO;
import com.example.HealthCare.model.Medecin;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponseDTO register(UserRegisterDTO userRegisterDTO) {

        User.Role role = User.Role.valueOf(userRegisterDTO.getRole().toUpperCase());

        User user;

        if (role == User.Role.PATIENT) {
            user = new Patient();
        } else if (role == User.Role.MEDECIN) {
            user = new Medecin();
        } else {
            user = new User();
        }

        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRole(role);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }

        User savedUser = userRepository.save(user);

        String token = jwtUtils.generateToken(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name()
        );

        return new LoginResponseDTO(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public LoginResponseDTO login(UserLoginDTO userLoginDTO) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),
                        userLoginDTO.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String token = jwtUtils.generateToken(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );

        return new LoginResponseDTO(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}