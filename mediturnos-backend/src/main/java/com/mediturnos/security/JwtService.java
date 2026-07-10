package com.mediturnos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET =
            "MediturnosEspecializacionIngenieriaSoftwareJWT2026SecretKey123456";

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8))
                .signWith(key)
                .compact();

    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {

        return extractUsername(token).equals(username)
                && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        ).before(new Date());

    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);

    }

}