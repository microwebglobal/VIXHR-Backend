package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.employee.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "departments")
public class Department extends Auditable {

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
    private String name;

    @NotNull
    @Length(min = 2, max = 50)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
