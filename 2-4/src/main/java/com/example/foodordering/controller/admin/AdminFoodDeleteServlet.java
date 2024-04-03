package com.example.foodordering.controller.admin;

import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "adminFoodDeleteServlet", value = "/admin/food/delete/*")
public class AdminFoodDeleteServlet extends MainLayoutServlet {
    @Inject
    private FoodService foodService;

    @Override
    protected void doPostSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long foodId = getFoodId(request);
        foodService.delete(foodId);

        response.sendRedirect(request.getHeader("referer"));
    }

    private long getFoodId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] pathParts = uri.split("/");
        return Long.parseLong(pathParts[4]);
    }
}
