package com.example.foodordering.service.impl;

import com.example.foodordering.config.AuthProperties;
import com.example.foodordering.service.JwtService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Stateless
public class JwtServiceImpl implements JwtService {
    @Inject
    private AuthProperties authProperties;

    @Override
    public String generate(String value) {
        return Jwts.builder()
                .id(value)
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        JwtParser parser = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build();
        try {
            parser.parse(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private SecretKey getSecretKey() {
        byte[] secretBytes = authProperties.getJwtSecretKey().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
