package com.example.foodordering.repository.impl;

import com.example.foodordering.common.ActionType;
import com.example.foodordering.common.EntityType;
import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.common.UserType;
import com.example.foodordering.entity.Category;
import com.example.foodordering.entity.Food;
import com.example.foodordering.repository.FoodRepository;
import com.example.foodordering.repository.PaginationRepository;
import com.example.foodordering.repository.ProjectAuditRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class FoodRepositoryImpl implements FoodRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private PaginationRepository paginationRepository;
    @Inject
    private ProjectAuditRepository projectAuditRepository;

    @Override
    public List<Food> findByIds(List<Long> ids) {
        return entityManager
                .createQuery("SELECT f FROM Food f WHERE f.id IN :ids", Food.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public Page<Food> findItemsOrderByIdDesc(PageRequest pageRequest) {
        TypedQuery<Food> query = entityManager.createQuery("SELECT f FROM Food f ORDER BY f.id DESC", Food.class);
        Query countQuery = entityManager.createQuery("SELECT COUNT(f.id) FROM Food f");
        return paginationRepository.findPage(query, countQuery, pageRequest);
    }

    @Override
    public Page<Food> findItemsByCategoryIdOrderByIdDesc(long categoryId, PageRequest pageRequest) {
        TypedQuery<Food> query = entityManager.createQuery("SELECT f FROM Food f WHERE f.category.id = :categoryId ORDER BY f.id DESC", Food.class)
                .setParameter("categoryId", categoryId);
        Query countQuery = entityManager.createQuery("SELECT COUNT(f.id) FROM Food f");
        return paginationRepository.findPage(query, countQuery, pageRequest);
    }

    @Override
    public Optional<Food> findById(long id) {
        Food item = entityManager.createQuery("SELECT f FROM Food f WHERE f.id = :id", Food.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(item);
    }

    @Override
    public long update(Food food) {
        Food merged = entityManager.merge(food);
        entityManager.flush();
        projectAuditRepository.save(UserType.ADMIN, EntityType.FOOD, ActionType.UPDATE, food.getId());
        return merged.getId();
    }

    @Override
    public long save(Food food) {
        entityManager.persist(food);
        entityManager.flush();
        projectAuditRepository.save(UserType.ADMIN, EntityType.FOOD, ActionType.CREATE, food.getId());
        return food.getId();
    }

    @Override
    public long deleteById(long id) {
        projectAuditRepository.save(UserType.ADMIN, EntityType.FOOD, ActionType.DELETE, id);
        return entityManager.createNativeQuery("DELETE FROM public.food WHERE id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }
}
