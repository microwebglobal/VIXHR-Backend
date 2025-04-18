package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "job_roles")
public class JobRole extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Positive
    @Column(unique = true, name = "company_id")
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 50)
    @Column(unique = true)
    private String title;

    @Length(min = 2, max = 50)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double MinSalary;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double MaxSalary;
}
