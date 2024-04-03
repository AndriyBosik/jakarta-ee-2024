package com.example.foodordering.controller.cart;

import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.CartItemDto;
import com.example.foodordering.dto.OrderDto;
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

@WebServlet(name = "cartViewServlet", value = "/cart/view")
public class CartViewServlet extends MainLayoutServlet {
    @Inject
    private OrderService orderService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderView("cart/view", request, response);
    }

    @Override
    protected void doPostSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        List<Long> ids = Arrays.stream(request.getParameterValues("id"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<Integer> quantities = Arrays.stream(request.getParameterValues("quantity"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<CartItemDto> items = IntStream.range(0, ids.size())
                .mapToObj(index -> new CartItemDto(ids.get(index), quantities.get(index)))
                .collect(Collectors.toList());

        OrderDto order = new OrderDto(firstName, lastName, phone, address, items);

        long orderId = orderService.save(order);

        request.getSession().removeAttribute("cart");

        response.sendRedirect("/cart/successful?orderId=" + orderId);
    }
}
