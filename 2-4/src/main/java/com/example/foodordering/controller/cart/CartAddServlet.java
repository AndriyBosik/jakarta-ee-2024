package com.example.foodordering.controller.cart;

import com.example.foodordering.dto.FoodCartDto;
import com.example.foodordering.metadata.Route;
import com.example.foodordering.service.CartService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "cartServlet", value = Route.CART_ADD)
public class CartAddServlet extends HttpServlet {
    @Inject
    private CartService cartService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long foodId = Long.parseLong(request.getParameter("foodId"));
        Map<Long, FoodCartDto> cart = cartService.add(foodId, getCart(request));
        refreshCart(request, cart);
        response.sendRedirect(request.getHeader("referer"));
    }

    private Map<Long, FoodCartDto> getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new HashMap<>());
        }
        return (Map<Long, FoodCartDto>) session.getAttribute("cart");
    }

    private void refreshCart(HttpServletRequest request, Map<Long, FoodCartDto> cart) {
        request.getSession().setAttribute("cart", cart);
    }
}
