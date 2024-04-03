package com.example.foodordering.controller.admin;

import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.AggregatedOrderDto;
import com.example.foodordering.service.OrderService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminOrdersViewServlet", value = "/admin/orders/view")
public class AdminOrdersViewServlet extends MainLayoutServlet {
    @Inject
    private OrderService orderService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AggregatedOrderDto> orders = orderService.findOrders();
        request.setAttribute("orders", orders);
        renderView("admin/orders/view", request, response);
    }
}
