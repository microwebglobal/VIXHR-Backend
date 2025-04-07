package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Length(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 2, max = 50)
    private String department;

    @NotNull
    @Length(min = 2, max = 50)
    private String jobTitle;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double baseSalary;
}