package com.example.foodordering.repository.impl;

import com.example.foodordering.entity.Category;
import com.example.foodordering.repository.CategoryRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Category c ORDER BY c.id", Category.class)
                .getResultList();
    }
}
