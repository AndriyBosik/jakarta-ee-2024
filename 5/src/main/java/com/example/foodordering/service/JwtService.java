package com.example.foodordering.service;

public interface JwtService {
    String generate(String value);

    boolean validate(String token);
}
