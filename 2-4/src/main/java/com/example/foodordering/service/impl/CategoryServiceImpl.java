package com.example.foodordering.service.impl;

import com.example.foodordering.dto.CategoryDto;
import com.example.foodordering.mapper.CategoryMapper;
import com.example.foodordering.repository.CategoryRepository;
import com.example.foodordering.service.CategoryService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
