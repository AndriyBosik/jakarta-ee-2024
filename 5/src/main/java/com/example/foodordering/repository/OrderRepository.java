package com.example.foodordering.repository;

import com.example.foodordering.entity.Order;
import com.example.foodordering.entity.projection.AggregatedOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    long save(Order order);

    List<AggregatedOrder> findOrders();

    Optional<Order> findById(long id);

    long update(Order order);
}
