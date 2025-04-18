package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "employees")
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Column(name = "company_id")
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @Length(min = 10, max = 10)
    private String phone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate terminationDate;

    @Column(unique = true)
    private String employeeCode;

    @NotNull
    private Double baseSalary;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_role_id")
    private JobRole jobRole;
}