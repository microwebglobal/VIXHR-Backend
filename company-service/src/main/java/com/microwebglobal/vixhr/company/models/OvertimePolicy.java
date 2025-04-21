package com.microwebglobal.vixhr.company.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "overtime_policies")
public class OvertimePolicy extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double rateMultiplier;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Column(columnDefinition = "jsonb")
    private String applicableDays;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerDay;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerWeek;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerMonth;

    private boolean isHoliday = false;

    private boolean requiresApproval = true;
}
