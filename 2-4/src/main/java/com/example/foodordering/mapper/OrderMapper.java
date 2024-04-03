package com.example.foodordering.mapper;

import com.example.foodordering.dto.AggregatedOrderDto;
import com.example.foodordering.dto.OrderViewDto;
import com.example.foodordering.entity.Order;
import com.example.foodordering.entity.projection.AggregatedOrder;
import org.mapstruct.Mapper;

@Mapper(uses = OrderItemMapper.class)
public interface OrderMapper {
    AggregatedOrderDto toDto(AggregatedOrder projection);

    OrderViewDto toViewDto(Order entity);
}
