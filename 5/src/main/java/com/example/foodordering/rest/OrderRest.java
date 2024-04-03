package com.example.foodordering.rest;

import com.example.foodordering.dto.AggregatedOrderDto;
import com.example.foodordering.dto.IdDto;
import com.example.foodordering.dto.OrderDto;
import com.example.foodordering.dto.OrderViewDto;
import com.example.foodordering.dto.UpdateOrderDto;
import com.example.foodordering.service.OrderService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/orders")
public class OrderRest {
    @Inject
    private OrderService orderService;

    @POST
    public Response save(OrderDto orderDto) {
        long id = orderService.save(orderDto);
        return Response
                .status(Response.Status.CREATED)
                .entity(new IdDto(id))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        OrderViewDto order = orderService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return Response
                .ok(order)
                .build();
    }

    @PUT
    public Response update(UpdateOrderDto orderDto) {
        long updatedId = orderService.update(orderDto);
        return Response
                .status(Response.Status.OK)
                .entity(new IdDto(updatedId))
                .build();
    }

    @GET
    @Path("/aggregated")
    public Response findAggregated() {
        List<AggregatedOrderDto> orders = orderService.findOrders();
        return Response
                .status(Response.Status.OK)
                .entity(orders)
                .build();
    }
}
