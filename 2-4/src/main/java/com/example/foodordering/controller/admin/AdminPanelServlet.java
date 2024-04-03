package com.example.foodordering.controller.admin;

import com.example.foodordering.common.Page;
import com.example.foodordering.common.PageRequest;
import com.example.foodordering.controller.base.MainLayoutServlet;
import com.example.foodordering.dto.ProjectAuditDto;
import com.example.foodordering.service.AuditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "adminPanelServlet", value = "/admin/panel")
public class AdminPanelServlet extends MainLayoutServlet {
    @Inject
    private AuditService auditService;

    @Override
    protected void doGetSafe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Optional.ofNullable(request.getParameter("page"))
                .map(Integer::parseInt)
                .orElse(0);
        PageRequest pageRequest = new PageRequest(pageNumber, 10);
        Page<ProjectAuditDto> auditPage = auditService.findAll(pageRequest);

        request.setAttribute("audit", auditPage);
        renderView("admin/panel", request, response);
    }
}
