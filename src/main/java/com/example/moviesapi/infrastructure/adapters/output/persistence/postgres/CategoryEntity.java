package com.example.moviesapi.infrastructure.adapters.output.persistence.postgres;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("categories")
public class CategoryEntity {

    @Id
    private Long id;

    private String name;
}