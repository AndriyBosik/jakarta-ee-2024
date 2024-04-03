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
    private final long totalElements;
    private final List<T> content;

    public <R> Page<R> map(Function<T, R> mapper) {
        List<R> newContent = content.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new Page<>(
                pageNumber,
                pageSize,
                totalElements,
                newContent);
    }

    public int getTotalPages() {
        if (pageSize == 0) {
            return 0;
        }
        return (int) Math.ceil(totalElements * 1.0 / pageSize);
    }
}
