package com.example.moviesapi.infrastructure.adapters.output.persistence.postgres;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("users")
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String password;

    private String role;
}