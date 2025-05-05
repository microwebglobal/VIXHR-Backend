package com.microwebglobal.vixhr.attendance.dto;

public record EmployeeResponse(
        Long id,
        String userId,
        Long companyId,
        String employeeCode,
        String firstName,
        String lastName,
        Double baseSalary,
        DepartmentResponse department,
        JobRoleResponse jobRole
) { }