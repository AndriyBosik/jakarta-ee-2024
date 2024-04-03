package com.example.foodordering.service.impl;

import com.example.foodordering.dto.FoodCartDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.mapper.FoodMapper;
import com.example.foodordering.service.CartService;
import com.example.foodordering.service.FoodService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Singleton
public class CartServiceImpl implements CartService {
    @Inject
    private FoodService foodService;
    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Override
    public Map<Long, FoodCartDto> add(long foodId, Map<Long, FoodCartDto> cart) {
        FoodDto food = foodService.findById(foodId)
                .orElse(null);
        if (food == null) {
            return cart;
        }
        if (cart.get(foodId) != null) {
            cart.get(foodId).setQuantity(cart.get(foodId).getQuantity() + 1);
        } else {
            cart.put(foodId, foodMapper.toCartDto(1, food));
        }
        return cart;
    }

    @Override
    public int getTotalFoodQuantity(Map<Long, FoodCartDto> cart) {
        return cart.values().stream()
                .map(FoodCartDto::getQuantity)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
