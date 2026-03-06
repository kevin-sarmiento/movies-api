package com.example.moviesapi.infrastructure.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        try {
            String token = authentication.getCredentials().toString();
            Claims claims = jwtService.extractClaims(token);

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            List<SimpleGrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_" + role));

            return Mono.just(
                    new UsernamePasswordAuthenticationToken(username, token, authorities)
            );
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}