package com.example.foodordering.repository;

import com.example.foodordering.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);
}
