
-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create an index on username for faster lookups
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);

-- Create an index on email for faster lookups
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);

-- Insert initial admin user (password is 'admin123' bcrypted)
INSERT INTO users (username, password, email, role)
VALUES ('admin', '$2a$10$y.hTz2oJQbA5Kf15KBRPvOxVKWH8DvdKMZkDYtryKvz8Y7f/LgYv.', 'admin@vixhr.com', 'ADMIN')
ON CONFLICT (username) DO NOTHING;