package com.example.moviesapi.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtServerAuthenticationConverter jwtServerAuthenticationConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        jwtFilter.setServerAuthenticationConverter(jwtServerAuthenticationConverter);

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/movies/**").hasAnyRole("USER", "ADMIN")
                        .pathMatchers(HttpMethod.POST, "/movies/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/movies/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/movies/**").hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/categories/**").hasAnyRole("USER", "ADMIN")
                        .pathMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")

                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)                .build();
    }
}