package com.example.foodordering.dto.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiFoodCreateDto {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String originalImagePath;
    private String imagePath;
    private BigDecimal price;
}
