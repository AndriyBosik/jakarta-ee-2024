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
public class OrderFoodDto {
    private Long id;
    private String imagePath;
    private String originalImagePath;
    private String title;
    private String description;
    private BigDecimal price;
    private int quantity;
    private CategoryDto category;
}
