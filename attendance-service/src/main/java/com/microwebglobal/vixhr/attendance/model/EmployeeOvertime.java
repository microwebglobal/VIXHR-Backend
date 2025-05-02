package com.microwebglobal.vixhr.attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_overtime")
public class EmployeeOvertime extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long companyId;

    @NotNull
    private Long overtimePolicyId;

    @NotNull
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotNull
    private double hours;

    @NotNull
    private double rate;

    @Length(min = 2, max = 20)
    private String status = "Pending";

    @Length(min = 2, max = 500)
    private String notes;

    private Long approvedBy;
    private LocalDate approvedDate;
}
