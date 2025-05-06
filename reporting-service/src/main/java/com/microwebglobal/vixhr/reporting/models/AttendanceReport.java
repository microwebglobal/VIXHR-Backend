package com.microwebglobal.vixhr.reporting.models;

import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "attendance_reports",
        indexes = {
                @Index(name = "idx_report_company_date", columnList = "companyId, date"),
                @Index(name = "idx_report_employee_date", columnList = "employeeId, date"),
                @Index(name = "idx_report_date", columnList = "date"),
                @Index(name = "idx_report_status", columnList = "status")
        }
)
public class AttendanceReport extends Auditable {

    @Id
    private Long attendanceId;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Long companyId;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Column(length = 50, nullable = false)
    private String employeeCode;

    @Column(length = 50, nullable = false)
    private String department;

    @Column(length = 50, nullable = false)
    private String jobRole;

    @DecimalMin("0")
    @Column( nullable = false)
    private Double baseSalary;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    @Column(length = 50, nullable = false)
    private String status;

    private Boolean isOvertime;
    private Double overtimeHours;
    private Double overtimeRate;

    @Column(length = 50)
    private String overtimeStatus;

    @Column(length = 200)
    private String notes;
}
