package com.example.foodordering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private List<CartItemDto> items = new ArrayList<>();
}
