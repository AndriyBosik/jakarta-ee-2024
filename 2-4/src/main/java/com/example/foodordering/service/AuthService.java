package com.example.foodordering.service;

import com.example.foodordering.dto.UserDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(String login, String password);
}
