CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE events (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    vacancy INT NOT NULL,
    date_start TIMESTAMP NOT NULL,
    date_end TIMESTAMP NOT NULL
);
