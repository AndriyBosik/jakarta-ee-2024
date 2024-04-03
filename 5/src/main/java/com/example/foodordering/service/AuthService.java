package com.example.foodordering.service;

import com.example.foodordering.dto.LoginDto;
import com.example.foodordering.dto.TokenDto;
import com.example.foodordering.dto.UserDto;

import java.util.Optional;

public interface AuthService {
    Optional<UserDto> login(String login, String password);

    TokenDto login(LoginDto loginDto);
}
