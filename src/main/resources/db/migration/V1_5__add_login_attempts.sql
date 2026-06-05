CREATE TABLE IF NOT EXISTS login_attempt (
    id          BIGSERIAL PRIMARY KEY,
    attempt_key VARCHAR(255)             NOT NULL UNIQUE,
    attempts    INTEGER                  NOT NULL DEFAULT 0,
    window_start TIMESTAMP WITH TIME ZONE NOT NULL
);
