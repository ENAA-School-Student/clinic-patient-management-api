package com.example.HealthCare.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtUtilsTest {

    private final JwtUtils jwtUtils = new JwtUtils();

    @Test
    void generateTokenReturnsValidToken() {
        String token = jwtUtils.generateToken(1L, "testuser", "PATIENT");
        assertNotNull(token);
        assertTrue(jwtUtils.validateToken(token));
    }

    @Test
    void extractClaimsFromToken() {
        String token = jwtUtils.generateToken(42L, "admin", "ADMIN");
        assertEquals(42L, jwtUtils.extractUserId(token));
        assertEquals("admin", jwtUtils.extractUsername(token));
        assertEquals("ADMIN", jwtUtils.extractUserRole(token));
    }

    @Test
    void differentTokensForDifferentUsers() {
        String tokenA = jwtUtils.generateToken(1L, "userA", "PATIENT");
        String tokenB = jwtUtils.generateToken(2L, "userB", "MEDECIN");
        assertFalse(tokenA.equals(tokenB));
    }

    @Test
    void invalidTokenIsRejected() {
        assertFalse(jwtUtils.validateToken("invalid.token.value"));
        assertFalse(jwtUtils.validateToken(""));
        assertFalse(jwtUtils.validateToken(null));
    }
}
