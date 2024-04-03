package com.example.foodordering.controller.base;

import com.example.foodordering.dto.FoodCartDto;
import com.example.foodordering.service.CartService;
import jakarta.ejb.EJBException;
import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class MainLayoutServlet extends LayoutServlet {
    @Inject
    private CartService cartService;

    public MainLayoutServlet() {
        super("main");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = null;
        try {
            doGetSafe(request, response);
        } catch (EntityNotFoundException exception) {
            redirect = String.format(
                    "%s?code=NOT_FOUND",
                    request.getHeader("referer"));
        } catch (EJBException exception) {
            if (exception.getCause() instanceof EJBTransactionRolledbackException) {
                redirect = String.format(
                        "%s?code=TRANSACTION_ROLLBACK",
                        request.getHeader("referer"));
            } else {
                throw exception;
            }
        }
        if (redirect != null) {
            response.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = null;
        try {
            doPostSafe(request, response);
        } catch (EntityNotFoundException exception) {
            redirect = String.format(
                    "%s?code=NOT_FOUND",
                    request.getHeader("referer"));
        } catch (EJBException exception) {
            if (exception.getCause() instanceof EJBTransactionRolledbackException) {
                redirect = String.format(
                        "%s?code=TRANSACTION_ROLLBACK",
                        request.getHeader("referer"));
            } else {
                throw exception;
            }
        }
        if (redirect != null) {
            response.sendRedirect(redirect);
        }
    }

    @Override
    protected void renderView(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Long, FoodCartDto> cart = Optional.ofNullable((Map<Long, FoodCartDto>) request.getSession().getAttribute("cart"))
                .orElse(new HashMap<>());
        int totalFoodQuantity = cartService.getTotalFoodQuantity(cart);
        request.setAttribute("totalFoodQuantity", totalFoodQuantity);
        super.renderView(view, request, response);
    }

    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPostSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
