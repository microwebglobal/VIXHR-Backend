
-- Attendance Records Table
CREATE TABLE IF NOT EXISTS attendance_records (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    date DATE NOT NULL,
    check_in_time TIME,
    check_out_time TIME,
    status VARCHAR(20) NOT NULL, -- Present, Absent, Half-day, etc.
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_attendance_employee_date ON attendance_records(employee_id, date);

CREATE INDEX IF NOT EXISTS idx_attendance_status ON attendance_records(status);

-- Leave Requests Table
CREATE TABLE IF NOT EXISTS leave_requests (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    leave_type VARCHAR(30) NOT NULL, -- Vacation, Sick, Personal, etc.
    status VARCHAR(20) NOT NULL, -- Pending, Approved, Rejected, etc.
    reason TEXT,
    approved_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_leave_employee_id ON leave_requests(employee_id);

CREATE INDEX IF NOT EXISTS idx_leave_status ON leave_requests(status);

CREATE INDEX IF NOT EXISTS idx_leave_date_range ON leave_requests(start_date, end_date);