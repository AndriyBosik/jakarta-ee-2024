package com.example.foodordering.service.impl;

import com.example.foodordering.common.OrderStatus;
import com.example.foodordering.dto.AggregatedOrderDto;
import com.example.foodordering.dto.CartItemDto;
import com.example.foodordering.dto.OrderDto;
import com.example.foodordering.dto.OrderViewDto;
import com.example.foodordering.dto.UpdateOrderDto;
import com.example.foodordering.entity.Food;
import com.example.foodordering.entity.Order;
import com.example.foodordering.entity.OrderItem;
import com.example.foodordering.mapper.OrderMapper;
import com.example.foodordering.repository.OrderRepository;
import com.example.foodordering.service.OrderService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderRepository orderRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public long save(OrderDto orderDto) {
        Order order = prepareOrder(orderDto);
        orderDto.getItems().stream()
                .filter(item -> item.getQuantity() > 0)
                .forEach(item -> processOrderItem(order, item));

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public long update(UpdateOrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        order.setFirstName(orderDto.getFirstName());
        order.setLastName(orderDto.getLastName());
        order.setPhone(orderDto.getPhone());
        order.setStatus(orderDto.getStatus());
        order.getItems().clear();
        orderDto.getItems()
                .forEach(item -> processOrderItem(order, item));
        return orderRepository.update(order);
    }

    @Override
    public List<AggregatedOrderDto> findOrders() {
        return orderRepository.findOrders().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderViewDto> findById(long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toViewDto);
    }

    private Order prepareOrder(OrderDto orderDto) {
        return new Order(
                null,
                orderDto.getFirstName(),
                orderDto.getLastName(),
                orderDto.getPhone(),
                orderDto.getAddress(),
                OrderStatus.PENDING,
                LocalDateTime.now(),
                new ArrayList<>());
    }

    private void processOrderItem(Order order, CartItemDto itemDto) {
        OrderItem orderItem = new OrderItem();
        Food food = new Food();
        food.setId(itemDto.getId());
        orderItem.setFood(food);
        orderItem.setQuantity(itemDto.getQuantity());
        orderItem.setOrder(order);
        order.getItems().add(orderItem);
    }
}
