package com.example.foodordering.dto;

import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodCreateDto {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String originalImagePath;
    private String imageName;
    private String imageContextPath;
    private BigDecimal price;
    private List<Part> parts = new ArrayList<>();
}
