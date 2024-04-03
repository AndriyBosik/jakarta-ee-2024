package com.example.foodordering.repository;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.entity.Food;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    List<Food> findByIds(List<Long> ids);

    Page<Food> findItems(PageRequest pageRequest);

    Page<Food> findItemsByCategoryIdOrderByIdDesc(long categoryId, PageRequest pageRequest);

    Optional<Food> findById(long id);

    long update(Food food);

    long save(Food food);

    long deleteById(long id);
}
