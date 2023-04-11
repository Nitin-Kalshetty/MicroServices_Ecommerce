package com.ecom_fin.cart.external.securityConfig.jwtToken.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IpAddressFilter extends OncePerRequestFilter{

    private String allowedIpAddress;

    public IpAddressFilter(String allowedIpAddress) {
        this.allowedIpAddress = allowedIpAddress;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
        String clientIpAddress = request.getRemoteAddr();

        if (clientIpAddress.equals(allowedIpAddress)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied");
        }

    }
    
}
