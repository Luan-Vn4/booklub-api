CREATE TABLE IF NOT EXISTS clubs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    club_name VARCHAR(50) NOT NULL UNIQUE,
    creation_date DATE NOT NULL,
    image_url VARCHAR,
    is_private BOOL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    image_url VARCHAR,
    email VARCHAR NOT NULL UNIQUE,
    password CHAR(60)
);

