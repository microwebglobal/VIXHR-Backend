package com.microwebglobal.vixhr.reporting.models;

import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance_reports")
public class AttendanceReport extends Auditable {

    @Id
    private Long attendanceId;

    private Long employeeId;
    private Long companyId;
    private String fullName;
    private String employeeCode;
    private String department;
    private String jobRole;
    private Double baseSalary;

    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String status;

    private Boolean isOvertime;
    private Double overtimeHours;
    private Double overtimeRate;
    private String overtimeStatus;
    private String notes;
}
