package com.ecom_fin.serviceRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class securityJWTconfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
        .formLogin()
        .and()
        .httpBasic();
    return security.build();

    }
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
