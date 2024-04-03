package com.example.foodordering.controller.admin;

import com.example.foodordering.common.OrderStatus;
import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.CartItemDto;
import com.example.foodordering.dto.OrderViewDto;
import com.example.foodordering.dto.UpdateOrderDto;
import com.example.foodordering.service.OrderService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet(name = "adminOrderViewServlet", value = "/admin/orders/view/*")
public class AdminOrderViewServlet extends MainLayoutServlet {
    @Inject
    private OrderService orderService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long orderId = getOrderId(request);
        OrderViewDto order = orderService.findById(orderId)
                .orElse(null);

        request.setAttribute("order", order);

        renderView("admin/order/view", request, response);
    }

    @Override
    protected void doPostSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("orderId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        OrderStatus status = OrderStatus.valueOf(request.getParameter("status"));

        List<Integer> quantities = Arrays.stream(request.getParameterValues("quantity"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Long> ids = Arrays.stream(request.getParameterValues("id"))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<CartItemDto> items = IntStream.range(0, ids.size())
                .mapToObj(index -> new CartItemDto(ids.get(index), quantities.get(index)))
                .collect(Collectors.toList());

        UpdateOrderDto orderDto = new UpdateOrderDto(id, firstName, lastName, phone, status, items);
        orderService.update(orderDto);

        response.sendRedirect(request.getHeader("referer"));
    }

    private long getOrderId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] pathParts = uri.split("/");
        return Long.parseLong(pathParts[4]);
    }
}
