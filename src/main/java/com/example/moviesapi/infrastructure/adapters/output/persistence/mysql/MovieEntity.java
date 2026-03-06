package com.example.moviesapi.infrastructure.adapters.output.persistence.mysql;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("movies")
public class MovieEntity {

    @Id
    private Long id;

    private String title;

    private String description;

    private Long categoryId;
}