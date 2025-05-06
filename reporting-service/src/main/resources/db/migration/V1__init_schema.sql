CREATE TABLE attendance_records
(
    attendance_id   BIGINT           NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    employee_id     BIGINT           NOT NULL,
    company_id      BIGINT           NOT NULL,
    full_name       VARCHAR(100)     NOT NULL,
    employee_code   VARCHAR(50)      NOT NULL,
    department      VARCHAR(50)      NOT NULL,
    job_role        VARCHAR(50)      NOT NULL,
    base_salary     DOUBLE PRECISION NOT NULL,
    date            date             NOT NULL,
    check_in_time   time WITHOUT TIME ZONE,
    check_out_time  time WITHOUT TIME ZONE,
    status          VARCHAR(50)      NOT NULL,
    is_overtime     BOOLEAN,
    overtime_hours  DOUBLE PRECISION,
    overtime_rate   DOUBLE PRECISION,
    overtime_status VARCHAR(50),
    notes           VARCHAR(200),
    record_status   VARCHAR(25),
    CONSTRAINT pk_attendance_records PRIMARY KEY (attendance_id)
);

CREATE INDEX idx_report_company_date ON attendance_records (company_id, date);

CREATE INDEX idx_report_date ON attendance_records (date);

CREATE INDEX idx_report_employee_date ON attendance_records (employee_id, date);

CREATE INDEX idx_report_status ON attendance_records (status);