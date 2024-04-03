package com.example.foodordering.service;

public interface EncryptionService {
    String encrypt(String value);

    boolean isValid(String value, String hash);
}
