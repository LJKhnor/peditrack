CREATE TABLE registration_key (
    id SERIAL PRIMARY KEY,
    key_value VARCHAR(50) UNIQUE NOT NULL,
    is_used BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE
);
