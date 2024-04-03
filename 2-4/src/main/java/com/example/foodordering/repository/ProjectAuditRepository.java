package com.example.foodordering.repository;

import com.example.foodordering.common.ActionType;
import com.example.foodordering.common.EntityType;
import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.common.UserType;
import com.example.foodordering.entity.ProjectAudit;

import java.util.Map;

public interface ProjectAuditRepository {
    Page<ProjectAudit> findAll(PageRequest pageRequest);

    long save(UserType userType, EntityType entityType, ActionType actionType);

    long save(UserType userType, EntityType entityType, ActionType actionType, Long entityId);
}
