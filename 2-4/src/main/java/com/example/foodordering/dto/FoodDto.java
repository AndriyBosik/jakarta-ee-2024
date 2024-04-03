package com.example.foodordering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    private Long id;
    private long categoryId;
    private String imagePath;
    private String originalImagePath;
    private String title;
    private String description;
    private BigDecimal price;
    private CategoryDto category;
}
