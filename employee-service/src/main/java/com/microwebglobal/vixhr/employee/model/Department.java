package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(unique = true, name = "company_id")
    private Long companyId;

    @NotNull
    @Column(unique = true)
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @Length(min = 2, max = 50)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
