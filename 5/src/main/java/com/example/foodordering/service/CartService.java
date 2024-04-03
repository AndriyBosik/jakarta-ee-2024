package com.example.foodordering.service;

import com.example.foodordering.dto.FoodCartDto;

import java.util.Map;

public interface CartService {
    Map<Long, FoodCartDto> add(long foodId, Map<Long, FoodCartDto> cart);

    int getTotalFoodQuantity(Map<Long, FoodCartDto> cart);
}
