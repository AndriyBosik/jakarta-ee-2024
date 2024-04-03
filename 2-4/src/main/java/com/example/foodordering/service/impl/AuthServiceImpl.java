package com.example.foodordering.service.impl;

import com.example.foodordering.dto.UserDto;
import com.example.foodordering.entity.User;
import com.example.foodordering.mapper.UserMapper;
import com.example.foodordering.repository.UserRepository;
import com.example.foodordering.service.AuthService;
import com.example.foodordering.service.EncryptionService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Singleton
public class AuthServiceImpl implements AuthService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private EncryptionService encryptionService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public Optional<UserDto> login(String login, String password) {
        User user = userRepository.findByLogin(login)
                .orElse(null);
        if (user == null || !encryptionService.isValid(password, user.getPassword())) {
            return Optional.empty();
        }
        return Optional.of(userMapper.toDto(user));
    }
}
