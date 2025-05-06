package com.microwebglobal.vixhr.reporting.dto;

import com.microwebglobal.vixhr.reporting.models.AttendanceReport;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AttendanceReportResponse {

    private Long reportId;

    private Long employeeId;
    private String employeeCode;
    private String fullName;
    private String department;
    private String jobRole;

    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String status;
    private boolean isOvertime;
    private double overtimeHours;
    private String notes;

    public static AttendanceReportResponse from(AttendanceReport entity) {
        return AttendanceReportResponse.builder()
                .reportId(entity.getAttendanceId())
                .employeeId(entity.getEmployeeId())
                .employeeCode(entity.getEmployeeCode())
                .fullName(entity.getFullName())
                .department(entity.getDepartment())
                .jobRole(entity.getJobRole())
                .date(entity.getDate())
                .checkInTime(entity.getCheckInTime())
                .checkOutTime(entity.getCheckOutTime())
                .status(entity.getStatus())
                .isOvertime(entity.getIsOvertime())
                .overtimeHours(entity.getOvertimeHours() != null ? entity.getOvertimeHours() : 0.0)
                .notes(entity.getNotes() != null ? entity.getNotes() : "")
                .build();
    }
}
