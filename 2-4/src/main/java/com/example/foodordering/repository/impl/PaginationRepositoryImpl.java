package com.example.foodordering.repository.impl;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.repository.PaginationRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Singleton
public class PaginationRepositoryImpl implements PaginationRepository {
    @Override
    public <T> Page<T> findPage(TypedQuery<T> query, Query countQuery, PageRequest pageRequest) {
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());
        List<T> content = query.getResultList();
        long totalElements = (long) countQuery.getSingleResult();
        return new Page<>(
                pageRequest.getPageNumber(),
                pageRequest.getPageSize(),
                (int) Math.ceil(totalElements * 1.0 / pageRequest.getPageSize()),
                totalElements,
                content);
    }
}
