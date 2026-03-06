package com.example.moviesapi.infrastructure.adapters.input.controller;

import com.example.moviesapi.application.dto.AuthResponse;
import com.example.moviesapi.application.dto.LoginRequest;
import com.example.moviesapi.infrastructure.adapters.output.persistence.postgres.UserEntity;
import com.example.moviesapi.infrastructure.adapters.output.persistence.postgres.UserRepository;
import com.example.moviesapi.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/register")
    public Mono<UserEntity> register(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody LoginRequest request) {

        return userRepository.findByUsername(request.getUsername())
                .flatMap(user -> {

                    if (!user.getPassword().equals(request.getPassword())) {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }

                    String token = jwtService.generateToken(user.getUsername(), user.getRole());

                    return Mono.just(new AuthResponse(token));
                });
    }

}