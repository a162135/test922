package com.crm.settings.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains(".css") || uri.contains(".js")){
            return true;
        }
        if (uri.contains("login") || uri.contains("register")){
            return true;
        }
        if (request.getSession().getAttribute("user") != null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/static/login.html");
        return false;
    }
}
