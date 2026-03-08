CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS movies (
                                      id SERIAL PRIMARY KEY,
                                      title VARCHAR(255),
    description VARCHAR(255),
    category_id BIGINT
    );