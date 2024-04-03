package com.example.foodordering.rest;

import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.FoodDto;
import com.example.foodordering.dto.IdDto;
import com.example.foodordering.dto.api.ApiFoodCreateDto;
import com.example.foodordering.service.FoodService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/foods")
@Produces(MediaType.APPLICATION_JSON)
public class FoodRest {
    @Inject
    private FoodService foodService;

    @GET
    @Path("/newest")
    public Response findAll(@BeanParam PageRequest pageRequest) {
        return Response
                .ok(foodService.findNewest(pageRequest))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        FoodDto food = foodService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return Response
                .ok(food)
                .build();
    }

    @POST
    public Response create(ApiFoodCreateDto foodDto) {
        long id = foodService.create(foodDto);
        return Response
                .status(Response.Status.CREATED)
                .entity(new IdDto(id))
                .build();
    }

    @PUT
    public Response update(ApiFoodCreateDto foodDto) {
        long id = foodService.update(foodDto);
        return Response
                .status(Response.Status.OK)
                .entity(new IdDto(id))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        foodService.delete(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }
}
