package com.campusmaster.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key cle;
    private final long dureeExpirationMs;

    public JwtTokenProvider(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long dureeExpirationMs
    ) {
        this.cle = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.dureeExpirationMs = dureeExpirationMs;
    }

    public String genererToken(String email) {
        Date maintenant = new Date();
        Date expiration = new Date(maintenant.getTime() + dureeExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(maintenant)
                .setExpiration(expiration)
                .signWith(cle, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraireEmailDepuisToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(cle)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validerToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(cle)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
