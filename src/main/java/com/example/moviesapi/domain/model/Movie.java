package com.example.moviesapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private Long id;

    private String title;

    private String description;

    private Long categoryId;
}