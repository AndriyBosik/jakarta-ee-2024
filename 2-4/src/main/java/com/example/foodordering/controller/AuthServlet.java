package com.example.foodordering.controller;

import com.example.foodordering.controller.base.EmptyLayoutServlet;
import com.example.foodordering.dto.UserDto;
import com.example.foodordering.service.AuthService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authServlet", value = "/login")
public class AuthServlet extends EmptyLayoutServlet {
    @Inject
    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderView("auth/login", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDto user = authService.login(login, password)
                .orElse(null);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/menu");
    }
}
