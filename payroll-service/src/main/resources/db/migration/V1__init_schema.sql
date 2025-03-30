
-- Salary Components Table
CREATE TABLE IF NOT EXISTS salary_components (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    component_type VARCHAR(20) NOT NULL, -- Earning or Deduction
    taxable BOOLEAN DEFAULT TRUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert standard salary components
INSERT INTO salary_components (name, component_type, taxable, description)
VALUES
    ('Basic Salary', 'EARNING', TRUE, 'Base salary component'),
    ('House Rent Allowance', 'EARNING', FALSE, 'Allowance for housing'),
    ('Transport Allowance', 'EARNING', TRUE, 'Allowance for transportation'),
    ('Medical Allowance', 'EARNING', FALSE, 'Allowance for medical expenses'),
    ('Income Tax', 'DEDUCTION', FALSE, 'Income tax deduction'),
    ('Pension Fund', 'DEDUCTION', FALSE, 'Contribution to pension fund'),
    ('Health Insurance', 'DEDUCTION', FALSE, 'Health insurance premium')
ON CONFLICT (name) DO NOTHING;

-- Employee Salary Structure Table
CREATE TABLE IF NOT EXISTS employee_salary_structure (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    component_id BIGINT NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    effective_from DATE NOT NULL,
    effective_to DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_component FOREIGN KEY (component_id) REFERENCES salary_components(id)
);

CREATE INDEX IF NOT EXISTS idx_salary_employee_id ON employee_salary_structure(employee_id);
CREATE INDEX IF NOT EXISTS idx_salary_component_id ON employee_salary_structure(component_id);
CREATE INDEX IF NOT EXISTS idx_salary_effective_date ON employee_salary_structure(effective_from, effective_to);

-- Payroll Table
CREATE TABLE IF NOT EXISTS payrolls (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    payroll_period VARCHAR(7) NOT NULL, -- Format: YYYY-MM
    gross_salary DECIMAL(12, 2) NOT NULL,
    total_deductions DECIMAL(12, 2) NOT NULL,
    net_salary DECIMAL(12, 2) NOT NULL,
    status VARCHAR(20) NOT NULL, -- Draft, Processed, Paid, etc.
    payment_date DATE,
    payment_method VARCHAR(30),
    payment_reference VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_payroll_employee_period ON payrolls(employee_id, payroll_period);

CREATE INDEX IF NOT EXISTS idx_payroll_status ON payrolls(status);

CREATE TABLE IF NOT EXISTS payroll_items (
    id BIGSERIAL PRIMARY KEY,
    payroll_id BIGINT NOT NULL,
    component_id BIGINT NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payroll FOREIGN KEY (payroll_id) REFERENCES payrolls(id) ON DELETE CASCADE,
    CONSTRAINT fk_payroll_component FOREIGN KEY (component_id) REFERENCES salary_components(id)
);

CREATE INDEX IF NOT EXISTS idx_payroll_items_payroll_id ON payroll_items(payroll_id);