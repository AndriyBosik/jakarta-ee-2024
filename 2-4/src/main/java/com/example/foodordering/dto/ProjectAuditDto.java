package com.example.foodordering.dto;

import com.example.foodordering.common.ActionType;
import com.example.foodordering.common.EntityType;
import com.example.foodordering.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAuditDto {
    private Long id;
    private UserType userType;
    private EntityType entityType;
    private ActionType actionType;
    private long entityId;
    private LocalDateTime createdDate;
}
