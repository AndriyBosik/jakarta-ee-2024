package com.example.foodordering.config;

import jakarta.ejb.Stateless;
import lombok.Getter;

@Getter
@Stateless
public class AuthProperties {
    private final String jwtSecretKey = System.getenv("JWT_SECRET_KEY");
}
