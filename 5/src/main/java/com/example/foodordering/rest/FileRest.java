package com.example.foodordering.rest;

import com.example.foodordering.dto.PathDto;
import com.example.foodordering.service.FileService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;

@Path("/api/files")
@Produces(MediaType.APPLICATION_JSON)
public class FileRest {
    @Inject
    private FileService fileService;

    @POST
    @Path("/image")
    public Response test(
            @Context HttpServletRequest request,
            File file
    ) {
        String filepath = request.getServletContext().getRealPath("");
        String path = fileService.upload(filepath, file);
        return Response
                .status(Response.Status.CREATED)
                .entity(new PathDto(path))
                .build();
    }
}
