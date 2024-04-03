package com.example.foodordering.service;

import com.example.foodordering.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}
