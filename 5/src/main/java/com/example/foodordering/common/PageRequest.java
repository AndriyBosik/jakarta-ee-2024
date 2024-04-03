package com.example.foodordering.common;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    @QueryParam("pageNumber")
    private int pageNumber;

    @QueryParam("pageSize")
    private int pageSize;

    public int getOffset() {
        return pageNumber * pageSize;
    }
}
