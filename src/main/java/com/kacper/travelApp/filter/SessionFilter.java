package com.kacper.travelApp.filter;

import com.kacper.travelApp.service.Service.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SessionFilter implements Filter {

    private SessionService sessionService;

    public SessionFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (sessionService.isSessionActive(httpServletRequest.getSession().getId())) {
            System.out.println("Session is active: " + httpServletRequest.getSession().getId());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println("Session is not active: " + httpServletRequest.getSession().getId());
            httpServletResponse.sendError(401, "Your session has expired");
        }
     }
}
