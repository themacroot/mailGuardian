package com.sreekanth.mailGuardian.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



@Component
public class IPFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Inside Once Per Request Filter originated by request {}" + request.getLocalAddr());
        if(request.getLocalAddr().equals("0:0:0:0:0:0:0:1")||request.getLocalAddr().equals("127.0.0.1")) {
        filterChain.doFilter(request, response);
        }
        else {
        	response.sendError(401);
        }

    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }

}