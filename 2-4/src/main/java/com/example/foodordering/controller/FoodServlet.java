package com.example.foodordering.controller;

import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "foodServlet", value = "/food/*")
public class FoodServlet extends MainLayoutServlet {
    @Inject
    private FoodService foodService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long foodId = getFoodId(request);
        FoodDto food = foodService.findById(foodId).orElse(null);
        request.setAttribute("food", food);

        renderView("food/view", request, response);
    }

    private long getFoodId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] pathParts = uri.split("/");
        return Long.parseLong(pathParts[2]);
    }
}
