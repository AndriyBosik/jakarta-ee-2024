package com.example.foodordering.service.impl;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.FoodCreateDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.entity.Category;
import com.example.foodordering.entity.Food;
import com.example.foodordering.mapper.FoodMapper;
import com.example.foodordering.repository.FoodRepository;
import com.example.foodordering.service.FileService;
import com.example.foodordering.service.FoodService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class FoodServiceImpl implements FoodService {
    private static final String IMAGE_SHORT_PATH = "/resources/images/food";

    @Inject
    private FoodRepository foodRepository;
    @Inject
    private FileService fileService;
    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Override
    public List<FoodDto> find(List<Long> ids) {
        return foodRepository.findByIds(ids).stream()
                .map(foodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FoodDto> findNewest(PageRequest pageRequest) {
        return foodRepository.findItemsOrderByIdDesc(pageRequest)
                .map(foodMapper::toDto);
    }

    @Override
    public Page<FoodDto> findByCategoryId(long categoryId, PageRequest pageRequest) {
        return foodRepository.findItemsByCategoryIdOrderByIdDesc(categoryId, pageRequest)
                .map(foodMapper::toDto);
    }

    @Override
    public Optional<FoodDto> findById(long id) {
        return foodRepository.findById(id)
                .map(foodMapper::toDto);
    }

    @Override
    @Transactional
    public long update(FoodCreateDto foodDto) {
        Food food = new Food();
        food.setId(foodDto.getId());
        updateContent(foodDto, food);
        return foodRepository.update(food);
    }

    @Override
    @Transactional
    public long create(FoodCreateDto foodDto) {
        Food food = new Food();
        updateContent(foodDto, food);
        return foodRepository.save(food);
    }

    @Override
    @Transactional
    public long delete(long id) {
        return foodRepository.deleteById(id);
    }

    private void updateContent(FoodCreateDto foodDto, Food food) {
        food.setTitle(foodDto.getTitle());
        food.setDescription(foodDto.getDescription());
        food.setPrice(foodDto.getPrice());
        food.setCategoryId(foodDto.getCategoryId());
        if (!foodDto.getParts().isEmpty() && !StringUtils.isBlank(foodDto.getImageName())) {
            UUID folderId = UUID.randomUUID();
            fileService.upload(
                    foodDto.getParts(),
                    String.format("%s/%s/%s/", foodDto.getImageContextPath(), IMAGE_SHORT_PATH, folderId),
                    foodDto.getImageName());
            String shortPath = String.format("%s/%s/%s", IMAGE_SHORT_PATH, folderId, foodDto.getImageName());
            food.setOriginalImagePath(foodDto.getImageName());
            food.setImagePath(shortPath);
        }
    }
}
