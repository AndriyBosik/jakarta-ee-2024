package com.example.foodordering.service;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.FoodCreateDto;
import com.example.foodordering.dto.FoodDto;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    List<FoodDto> find(List<Long> ids);

    Page<FoodDto> findNewest(PageRequest pageRequest);

    Page<FoodDto> findByCategoryId(long categoryId, PageRequest pageRequest);

    Optional<FoodDto> findById(long id);

    long update(FoodCreateDto foodDto);

    long create(FoodCreateDto foodDto);

    long delete(long id);
}
