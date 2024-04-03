package com.example.foodordering.dto;

import com.example.foodordering.common.OrderStatus;
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
public class UpdateOrderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private OrderStatus status;
    private List<CartItemDto> items = new ArrayList<>();
}
