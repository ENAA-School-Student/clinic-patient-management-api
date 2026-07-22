package com.example.HealthCare.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String secret =
            "MaSuperCleSecreteTresTresLonguePourEtreSecurisee12345678901234567890";

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(Long id, String username, String role) {

        return Jwts.builder()
                .claim("id", id)
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long extractUserId(String token) {
        return extractClaims(token).get("id", Long.class);
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractUserRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {

        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}