package com.campus.api;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jarno on 16/7/1.
 */
@Component
public class BasicFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterchain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        response.addHeader("Access-Control-Allow-Methods", "POST");

        response.addHeader("Access-Control-Max-Age", "30");

        response.addHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setHeader("Access-Control-Allow-Credentials","true");

        filterchain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}