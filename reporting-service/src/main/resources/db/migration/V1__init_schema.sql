
-- Reports Table
CREATE TABLE IF NOT EXISTS reports (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    report_type VARCHAR(50) NOT NULL,
    description TEXT,
    parameters JSONB,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_reports_type ON reports(report_type);

CREATE INDEX IF NOT EXISTS idx_reports_created_by ON reports(created_by);

CREATE TABLE IF NOT EXISTS report_schedules (
    id BIGSERIAL PRIMARY KEY,
    report_id BIGINT NOT NULL,
    schedule_type VARCHAR(20) NOT NULL, -- Daily, Weekly, Monthly, etc.
    cronexpression VARCHAR(100),
    active BOOLEAN DEFAULT TRUE,
    recipients TEXT[], -- Array of email addresses
    last_run_at TIMESTAMP,
    next_run_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_report FOREIGN KEY (report_id) REFERENCES reports(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_schedules_report_id ON report_schedules(report_id);

CREATE INDEX IF NOT EXISTS idx_schedules_next_run ON report_schedules(active, next_run_at) WHERE active = TRUE;

CREATE TABLE IF NOT EXISTS report_executions (
    id BIGSERIAL PRIMARY KEY,
    report_id BIGINT NOT NULL,
    schedule_id BIGINT,
    status VARCHAR(20) NOT NULL, -- Pending, Completed, Failed, etc.
    parameters JSONB,
    result_file_path VARCHAR(255),
    error_message TEXT,
    started_at TIMESTAMP,
    completed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_execution_report FOREIGN KEY (report_id) REFERENCES reports(id) ON DELETE CASCADE,
    CONSTRAINT fk_execution_schedule FOREIGN KEY (schedule_id) REFERENCES report_schedules(id) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_executions_report_id ON report_executions(report_id);

CREATE INDEX IF NOT EXISTS idx_executions_status ON report_executions(status);

CREATE INDEX IF NOT EXISTS idx_executions_dates ON report_executions(started_at, completed_at);