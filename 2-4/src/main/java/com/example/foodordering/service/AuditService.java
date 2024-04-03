package com.example.foodordering.service;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.ProjectAuditDto;

public interface AuditService {
    Page<ProjectAuditDto> findAll(PageRequest pageRequest);
}
