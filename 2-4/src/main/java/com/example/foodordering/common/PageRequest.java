package com.example.foodordering.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageRequest {
    private final int pageNumber;
    private final int pageSize;

    public int getOffset() {
        return pageNumber * pageSize;
    }
}
