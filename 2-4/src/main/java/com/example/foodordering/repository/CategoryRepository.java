package com.example.foodordering.repository;

import com.example.foodordering.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
}
