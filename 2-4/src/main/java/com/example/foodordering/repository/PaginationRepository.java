package com.example.foodordering.repository;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public interface PaginationRepository {
    <T> Page<T> findPage(
            TypedQuery<T> query,
            Query countQuery,
            PageRequest pageRequest);
}
