package com.example.foodordering.controller.cart;

import com.example.foodordering.controller.base.MainLayoutServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "cartSuccessfulServlet", value = "/cart/successful")
public class CartSuccessfulServlet extends MainLayoutServlet {
    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        request.setAttribute("orderId", orderId);
        renderView("cart/successful", request, response);
    }
}
