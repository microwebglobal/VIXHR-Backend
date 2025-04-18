package com.microwebglobal.vixhr.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "holidays")
public class Holiday extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Length(min = 2, max = 100)
    private String name;

    @NotNull
    private LocalDate date;

    private boolean isRecurring = false;

    @Length(min = 2, max = 100)
    private String description;
}
