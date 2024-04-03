package com.example.foodordering.service.impl;

import com.example.foodordering.service.EncryptionService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public String encrypt(String value) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(value, salt);
    }

    @Override
    public boolean isValid(String value, String hash) {
        return BCrypt.checkpw(value, hash);
    }
}
