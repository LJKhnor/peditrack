CREATE TABLE IF NOT EXISTS login_audit (
    id             BIGSERIAL PRIMARY KEY,
    username       VARCHAR(255)             NOT NULL,
    ip             VARCHAR(100)             NOT NULL,
    success        BOOLEAN                  NOT NULL,
    failure_reason VARCHAR(50)              NULL,
    attempted_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_login_audit_username ON login_audit (username);
CREATE INDEX IF NOT EXISTS idx_login_audit_ip       ON login_audit (ip);
CREATE INDEX IF NOT EXISTS idx_login_audit_attempted_at ON login_audit (attempted_at);
