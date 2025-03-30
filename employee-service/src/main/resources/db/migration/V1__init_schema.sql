
-- Employees Table
CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(50),
    job_title VARCHAR(100),
    joining_date DATE NOT NULL,
    base_salary DECIMAL(12, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create an index on user_id for faster lookups
CREATE INDEX IF NOT EXISTS idx_employees_user_id ON employees(user_id);

-- Create an index on email for faster lookups
CREATE INDEX IF NOT EXISTS idx_employees_email ON employees(email);

-- Department Table
CREATE TABLE IF NOT EXISTS departments (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    manager_id BIGINT,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--  foreign key constraint to link the manager to an employee
ALTER TABLE departments
ADD CONSTRAINT fk_departments_manager
FOREIGN KEY (manager_id) REFERENCES employees(id)
ON DELETE SET NULL;

-- Insert some default departments
INSERT INTO departments (name, description)
VALUES
    ('Engineering', 'Software development and engineering'),
    ('Finance', 'Financial operations and management'),
    ('Human Resources', 'Employee management and relations'),
    ('Marketing', 'Brand management and marketing operations'),
    ('Operations', 'Business operations')
ON CONFLICT (name) DO NOTHING;