package com.example.foodordering.controller.admin;

import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.CategoryDto;
import com.example.foodordering.dto.FoodCreateDto;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.service.CategoryService;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100)
@WebServlet(name = "adminFoodUpdateServlet", value = "/admin/food/update/*")
public class AdminFoodUpdateServlet extends MainLayoutServlet {
    @Inject
    private FoodService foodService;
    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        long foodId = getFoodId(request);
        FoodDto food = foodService.findById(foodId)
                .orElseThrow(EntityNotFoundException::new);
        List<CategoryDto> categories = categoryService.findAll();

        request.setAttribute("id", id);
        request.setAttribute("food", food);
        request.setAttribute("categories", categories);
        renderView("admin/food/create", request, response);
    }

    @Override
    protected void doPostSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part imagePart = request.getPart("image");
        String imageName = imagePart.getSubmittedFileName();

        long id = Long.parseLong(request.getParameter("id"));
        long categoryId = Long.parseLong(request.getParameter("category-id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String originalImagePath = request.getParameter("original-image-path");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        List<Part> imageParts = request.getParts().stream()
                .filter(part -> "image".equals(part.getName()))
                .collect(Collectors.toList());

        FoodCreateDto foodDto = new FoodCreateDto(
                id,
                categoryId,
                title,
                description,
                originalImagePath,
                imageName,
                getServletContext().getRealPath(""),
                price,
                imageParts);
        foodService.update(foodDto);

        response.sendRedirect(
                String.format(
                        "%s?id=%s",
                        request.getHeader("referer"),
                        id));
    }

    private long getFoodId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] pathParts = uri.split("/");
        return Long.parseLong(pathParts[4]);
    }
}
