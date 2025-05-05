CREATE TABLE attendance_reports
(
    attendance_id   BIGINT NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    employee_id     BIGINT,
    company_id      BIGINT,
    full_name       VARCHAR(255),
    employee_code   VARCHAR(255),
    department      VARCHAR(255),
    job_role        VARCHAR(255),
    base_salary     DOUBLE PRECISION,
    date            date,
    check_in_time   time WITHOUT TIME ZONE,
    check_out_time  time WITHOUT TIME ZONE,
    status          VARCHAR(255),
    is_overtime     BOOLEAN,
    overtime_hours  DOUBLE PRECISION,
    overtime_rate   DOUBLE PRECISION,
    overtime_status VARCHAR(255),
    notes           VARCHAR(255),
    CONSTRAINT pk_attendance_reports PRIMARY KEY (attendance_id)
);