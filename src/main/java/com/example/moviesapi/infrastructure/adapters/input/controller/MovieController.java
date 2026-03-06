package com.example.moviesapi.infrastructure.adapters.input.controller;

import com.example.moviesapi.infrastructure.adapters.output.persistence.mysql.MovieEntity;
import com.example.moviesapi.infrastructure.adapters.output.persistence.mysql.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping
    public Flux<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<MovieEntity> getMovieById(@PathVariable Long id) {
        return movieRepository.findById(id);
    }

    @PostMapping
    public Mono<MovieEntity> createMovie(@RequestBody MovieEntity movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Mono<MovieEntity> updateMovie(@PathVariable Long id, @RequestBody MovieEntity movie) {
        return movieRepository.findById(id)
                .flatMap(existing -> {
                    existing.setTitle(movie.getTitle());
                    existing.setDescription(movie.getDescription());
                    existing.setCategoryId(movie.getCategoryId());
                    return movieRepository.save(existing);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovie(@PathVariable Long id) {
        return movieRepository.deleteById(id);
    }
}