package com.ecom_fin.cart.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecom_fin.cart.external.securityConfig.jwtToken.filter.IpAddressFilter;




@Configuration
public class SecurityConfig {

    private String allowedIpAddress = "API-GATEWAY"; 
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(new IpAddressFilter(allowedIpAddress), UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .and()
            .httpBasic();

        return http.build();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }

}
