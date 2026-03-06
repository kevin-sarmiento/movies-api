package com.example.moviesapi.infrastructure.adapters.output.persistence.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findByUsername(String username);

}