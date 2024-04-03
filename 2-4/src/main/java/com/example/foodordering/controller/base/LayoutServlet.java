package com.example.foodordering.controller.base;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class LayoutServlet extends HttpServlet {
    private final String layout;

    public LayoutServlet(String layout) {
        this.layout = layout;
    }

    protected void renderView(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", view);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                String.format(
                        "/WEB-INF/jsp/layout/%s.jsp",
                        layout));
        dispatcher.forward(request, response);
    }
}
