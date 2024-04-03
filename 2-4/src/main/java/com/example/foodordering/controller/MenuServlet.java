package com.example.foodordering.controller;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.CategoryDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.metadata.Route;
import com.example.foodordering.service.CategoryService;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "menuServlet", value = {Route.MENU, Route.MENU_ITEM})
public class MenuServlet extends MainLayoutServlet {
    @Inject
    private CategoryService categoryService;
    @Inject
    private FoodService foodService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = categoryService.findAll();
        request.setAttribute("categories", categories);

        Long categoryId = getCategoryId(request);
        request.setAttribute("selectedCategoryId", categoryId);

        Page<FoodDto> foodPage = categoryId == null
                ? foodService.findNewest(new PageRequest(0, 5))
                : foodService.findByCategoryId(categoryId, new PageRequest(0, 5));
        request.setAttribute("foodPage", foodPage);

        String errorCode = request.getParameter("code");
        request.setAttribute("errorCode", errorCode);

        renderView("menu", request, response);
    }

    private Long getCategoryId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (Route.MENU.equals(uri)) {
            return null;
        }
        String[] pathParts = uri.split("/");
        return Long.parseLong(pathParts[2]);
    }
}
