package com.example.moviesapi.infrastructure.adapters.input.controller;

import com.example.moviesapi.infrastructure.adapters.output.persistence.postgres.CategoryEntity;
import com.example.moviesapi.infrastructure.adapters.output.persistence.postgres.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public Flux<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public Mono<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
        return categoryRepository.save(category);
    }
}