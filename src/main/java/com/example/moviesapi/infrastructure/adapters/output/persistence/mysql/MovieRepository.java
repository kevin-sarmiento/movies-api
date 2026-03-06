package com.example.moviesapi.infrastructure.adapters.output.persistence.mysql;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveCrudRepository<MovieEntity, Long> {

    Flux<MovieEntity> findByCategoryId(Long categoryId);

}