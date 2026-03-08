package com.example.moviesapi.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final ConnectionFactory connectionFactory;

    @Override
    public void run(String... args) {
        DatabaseClient client = DatabaseClient.create(connectionFactory);

        client.sql("""
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    username VARCHAR(255),
                    password VARCHAR(255),
                    role VARCHAR(50)
                );
                """).fetch().rowsUpdated().block();

        client.sql("""
                CREATE TABLE IF NOT EXISTS movies (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(255),
                    description VARCHAR(255),
                    category_id BIGINT
                );
                """).fetch().rowsUpdated().block();

        client.sql("""
                CREATE TABLE IF NOT EXISTS categories (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255)
                );
                """).fetch().rowsUpdated().block();
    }
}