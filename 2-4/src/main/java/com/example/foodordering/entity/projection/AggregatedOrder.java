package com.example.foodordering.entity.projection;

import com.example.foodordering.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedOrder {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdDate;
}
