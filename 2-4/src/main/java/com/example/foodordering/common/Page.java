package com.example.foodordering.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Page<T> {
    private final int pageNumber;
    private final int pageSize;
    private final int totalPages;
    private final long totalElements;
    private final List<T> content;

    public <R> Page<R> map(Function<T, R> mapper) {
        List<R> newContent = content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new Page<>(
                pageNumber,
                pageSize,
                totalPages,
                totalElements,
                newContent);
    }
}
