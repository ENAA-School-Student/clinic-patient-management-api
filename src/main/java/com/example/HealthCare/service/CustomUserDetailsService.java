package com.example.HealthCare.service;

import com.example.HealthCare.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.HealthCare.model.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String roleWithPrefix = "ROLE_" + userEntity.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleWithPrefix);
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                Collections.singletonList(authority)
                );
    }
}
