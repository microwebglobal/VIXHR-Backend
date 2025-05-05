package com.microwebglobal.vixhr.common.events;

public record EmployeeUpdatedEvent(
        Long employeeId,
        Long companyId,
        String employeeCode,
        String fullName,
        String department,
        String jobRole,
        Double baseSalary
) {}
