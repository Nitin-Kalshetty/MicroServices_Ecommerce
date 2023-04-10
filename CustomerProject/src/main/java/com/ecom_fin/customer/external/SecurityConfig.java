package com.ecom_fin.customer.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ecom_fin.customer.external.securityConfig.jwtToken.JwtGenerator;
import com.ecom_fin.customer.external.securityConfig.jwtToken.JwtValidator;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/users/add").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterAfter(new JwtGenerator(),BasicAuthenticationFilter.class)
            .addFilterBefore(new JwtValidator(),BasicAuthenticationFilter.class)
            .formLogin()
            .and()
            .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
