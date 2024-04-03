package com.example.foodordering.rest;

import com.example.foodordering.dto.LoginDto;
import com.example.foodordering.dto.TokenDto;
import com.example.foodordering.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthRest {
    @Inject
    private AuthService authService;

    @POST
    @Path("/token")
    public Response login(LoginDto loginDto) {
        TokenDto tokenDto = authService.login(loginDto);
        return Response
                .ok(tokenDto)
                .build();
    }
}
