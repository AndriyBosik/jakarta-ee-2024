package com.example.foodordering.rest;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.CategoryDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.service.CategoryService;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/categories")
public class CategoryRest {
    @Inject
    private CategoryService categoryService;
    @Inject
    private FoodService foodService;

    @GET
    public Response findAll() {
        List<CategoryDto> categories = categoryService.findAll();
        return Response
                .ok(categories)
                .build();
    }

    @GET
    @Path("/{id}/foods")
    public Response findFoodByCategoryId(
            @PathParam("id") long id,
            @BeanParam PageRequest pageRequest
    ) {
        Page<FoodDto> foodsPage = foodService.findByCategoryId(id, pageRequest);
        return Response
                .ok(foodsPage)
                .build();
    }
}
