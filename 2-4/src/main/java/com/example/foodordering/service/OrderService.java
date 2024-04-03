package com.example.foodordering.service;

import com.example.foodordering.dto.AggregatedOrderDto;
import com.example.foodordering.dto.OrderDto;
import com.example.foodordering.dto.OrderViewDto;
import com.example.foodordering.dto.UpdateOrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    long save(OrderDto orderDto);

    long update(UpdateOrderDto orderDto);

    List<AggregatedOrderDto> findOrders();

    Optional<OrderViewDto> findById(long id);
}
