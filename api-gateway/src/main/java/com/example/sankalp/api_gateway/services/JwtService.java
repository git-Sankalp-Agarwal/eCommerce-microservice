package com.example.sankalp.api_gateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                            .verifyWith(getSecretKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
        return Long.valueOf(claims.getSubject());
    }


}
