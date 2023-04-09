package com.ecom_fin.products.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
        .addFilterBefore(new JwtTokenValidatorFilter(), null)
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
