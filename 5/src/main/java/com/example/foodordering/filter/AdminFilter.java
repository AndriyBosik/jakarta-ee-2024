package com.example.foodordering.filter;

import com.example.foodordering.service.JwtService;
import jakarta.inject.Inject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebFilter("/*")
public class AdminFilter extends HttpFilter {
    @Inject
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (!isRestricted(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }
        String authorization = httpRequest.getHeader("Authorization");
        if (!StringUtils.startsWithIgnoreCase(authorization, "Bearer ")) {
            httpResponse.sendError(Response.Status.FORBIDDEN.getStatusCode());
            return;
        }
        String token = authorization.split("Bearer ")[1];
        if (!jwtService.validate(token)) {
            httpResponse.sendError(Response.Status.FORBIDDEN.getStatusCode());
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isRestricted(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        return isRestricted(method, uri);
    }

    private boolean isRestricted(String method, String uri) {
        String canonicalUri = String.format("%s %s", method, uri);
        if (StringUtils.equalsAnyIgnoreCase(
                canonicalUri,
                "POST /api/foods",
                "PUT /api/foods",
                "PUT /api/orders",
                "GET /api/orders/aggregated",
                "GET /api/audit")
        ) {
            return true;
        }
        return StringUtils.startsWithAny(canonicalUri, "GET /api/orders/", "DELETE /api/foods/");
    }
}
