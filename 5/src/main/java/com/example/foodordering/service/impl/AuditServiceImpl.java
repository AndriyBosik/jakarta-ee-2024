package com.example.foodordering.service.impl;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.ProjectAuditDto;
import com.example.foodordering.mapper.ProjectAuditMapper;
import com.example.foodordering.repository.ProjectAuditRepository;
import com.example.foodordering.service.AuditService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.mapstruct.factory.Mappers;

@Stateless
public class AuditServiceImpl implements AuditService {
    @Inject
    private ProjectAuditRepository projectAuditRepository;
    private final ProjectAuditMapper projectAuditMapper = Mappers.getMapper(ProjectAuditMapper.class);

    @Override
    public Page<ProjectAuditDto> findAll(PageRequest pageRequest) {
        return projectAuditRepository.findAll(pageRequest)
                .map(projectAuditMapper::toDto);
    }
}
