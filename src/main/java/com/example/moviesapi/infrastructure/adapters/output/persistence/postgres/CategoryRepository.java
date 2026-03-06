package com.example.moviesapi.infrastructure.adapters.output.persistence.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryEntity, Long> {
}