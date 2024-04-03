package com.example.foodordering.rest;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.dto.ProjectAuditDto;
import com.example.foodordering.service.AuditService;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/audit")
@Produces(MediaType.APPLICATION_JSON)
public class AuditRest {
    @Inject
    private AuditService auditService;

    @GET
    public Response findAll(@BeanParam PageRequest pageRequest) {
        Page<ProjectAuditDto> auditPage = auditService.findAll(pageRequest);
        return Response
                .status(Response.Status.OK)
                .entity(auditPage)
                .build();
    }
}
