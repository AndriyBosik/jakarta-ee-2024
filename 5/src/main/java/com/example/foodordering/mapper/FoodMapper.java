package com.example.foodordering.mapper;

import com.example.foodordering.dto.FoodCartDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.dto.api.ApiFoodCreateDto;
import com.example.foodordering.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FoodMapper {
    @Mapping(target = "categoryId", source = "category.id")
    FoodDto toDto(Food entity);

    FoodCartDto toCartDto(int quantity, FoodDto dto);

    Food toEntity(ApiFoodCreateDto dto);
}
