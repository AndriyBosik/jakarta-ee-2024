package com.example.foodordering.mapper;

import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.dto.UserDto;
import com.example.foodordering.entity.Food;
import com.example.foodordering.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toDto(User entity);
}
