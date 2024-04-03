package com.example.foodordering.mapper;

import com.example.foodordering.dto.CategoryDto;
import com.example.foodordering.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto toDto(Category entity);
}
