package com.example.foodordering.mapper;

import com.example.foodordering.dto.ProjectAuditDto;
import com.example.foodordering.entity.ProjectAudit;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectAuditMapper {
    ProjectAuditDto toDto(ProjectAudit entity);
}
