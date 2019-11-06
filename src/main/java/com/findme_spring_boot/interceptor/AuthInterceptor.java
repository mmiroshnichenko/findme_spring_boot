package com.findme_spring_boot.interceptor;

import com.findme_spring_boot.exception.ForbiddenException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("USER") == null) {
            throw new ForbiddenException("Error: user is not authenticated");
        }
        return super.preHandle(request, response, handler);
    }
}
