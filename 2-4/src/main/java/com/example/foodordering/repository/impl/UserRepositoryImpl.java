package com.example.foodordering.repository.impl;

import com.example.foodordering.entity.User;
import com.example.foodordering.repository.UserRepository;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByLogin(String login) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE lower(u.login) LIKE :login", User.class)
                .setParameter("login", login.toLowerCase())
                .getSingleResult();
        return Optional.ofNullable(user);
    }
}
