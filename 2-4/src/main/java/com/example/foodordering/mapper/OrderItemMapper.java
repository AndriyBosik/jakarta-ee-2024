package com.example.foodordering.mapper;

import com.example.foodordering.dto.OrderFoodDto;
import com.example.foodordering.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderItemMapper {
    @Mapping(target = "id", source = "food.id")
    @Mapping(target = "imagePath", source = "food.imagePath")
    @Mapping(target = "title", source = "food.title")
    @Mapping(target = "description", source = "food.description")
    @Mapping(target = "price", source = "food.price")
    @Mapping(target = "category.id", source = "food.category.id")
    @Mapping(target = "category.title", source = "food.category.title")
    OrderFoodDto toFoodDto(OrderItem entity);
}
