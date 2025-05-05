package com.microwebglobal.vixhr.common.events;

import java.time.LocalDate;
import java.time.LocalTime;

public record AttendanceRecordedEvent(
        Long attendanceId,
        Long employeeId,
        Long companyId,
        String employeeCode,
        String fullName,
        String department,
        String jobRole,
        Double baseSalary,
        LocalDate date,
        LocalTime checkInTime,
        LocalTime checkOutTime,
        String status,
        String notes
) {}
