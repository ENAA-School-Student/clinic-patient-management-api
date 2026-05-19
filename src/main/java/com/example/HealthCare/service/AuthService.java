package com.example.HealthCare.service;

import com.example.HealthCare.dto.UserLoginDTO;
import com.example.HealthCare.dto.UserRegisterDTO;
import com.example.HealthCare.mapper.UserMapper;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.UserRepository;
import com.example.HealthCare.config.JwtUtils;
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

    @Autowired
    private UserMapper userMapper;

    public String register(UserRegisterDTO userRegisterDTO) {

        User user = userMapper.toUserR(userRegisterDTO);

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("L'utilisateur existe déjà");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtUtils.generateToken(user.getUsername() , user.getRole().name());
    }

    public String login(UserLoginDTO userLoginDTO) {
        User user = userMapper.toUserL(userLoginDTO);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return jwtUtils.generateToken(user.getUsername() , userRepository.findByUsername(user.getUsername()).get().getRole().name());
    }
}
