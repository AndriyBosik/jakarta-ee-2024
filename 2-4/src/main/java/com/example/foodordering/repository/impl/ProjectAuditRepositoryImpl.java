package com.example.foodordering.repository.impl;

import com.example.foodordering.common.ActionType;
import com.example.foodordering.common.EntityType;
import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.common.UserType;
import com.example.foodordering.entity.ProjectAudit;
import com.example.foodordering.repository.PaginationRepository;
import com.example.foodordering.repository.ProjectAuditRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;

@Singleton
public class ProjectAuditRepositoryImpl implements ProjectAuditRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private PaginationRepository paginationRepository;

    @Override
    public Page<ProjectAudit> findAll(PageRequest pageRequest) {
        TypedQuery<ProjectAudit> query = entityManager.createQuery("SELECT pa FROM ProjectAudit pa ORDER BY pa.id DESC", ProjectAudit.class);
        Query countQuery = entityManager.createQuery("SELECT COUNT(pa.id) FROM ProjectAudit pa");
        return paginationRepository.findPage(query, countQuery, pageRequest);
    }

    @Override
    public long save(UserType userType, EntityType entityType, ActionType actionType) {
        return save(userType, entityType, actionType, null);
    }

    @Override
    public long save(UserType userType, EntityType entityType, ActionType actionType, Long entityId) {
        ProjectAudit projectAudit = new ProjectAudit();
        projectAudit.setUserType(userType);
        projectAudit.setEntityType(entityType);
        projectAudit.setActionType(actionType);
        projectAudit.setEntityId(entityId);
        projectAudit.setCreatedDate(LocalDateTime.now());
        entityManager.persist(projectAudit);
        entityManager.flush();
        return projectAudit.getId();
    }
}
