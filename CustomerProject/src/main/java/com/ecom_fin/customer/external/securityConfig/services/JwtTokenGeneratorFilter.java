package com.ecom_fin.customer.external.securityConfig.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom_fin.customer.external.securityConfig.JwtSecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            SecretKey key = Keys.hmacShaKeyFor(JwtSecurityConstants.JWT_KEY.getBytes());
            String jwt = Jwts.builder()
                             .setIssuer("Rocker_Ecommerce")
                             .setSubject("JWT_TOKEN")
                             .claim("username",auth.getName())
                             .claim("role",auth.getAuthorities())
                             .setIssuedAt(new Date())
                             .setExpiration(new Date(new Date().getTime()+300000000))
                             .signWith(key)
                             .compact();

            response.setHeader(JwtSecurityConstants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        return !request.getServletPath().equals("/signIn");
    }


    private String getRole(Collection<? extends GrantedAuthority> collection){
        String roleString = "";
        for(GrantedAuthority authority : collection){
            roleString = authority.getAuthority();
        }
        

        return roleString;
    }

    
    
}
