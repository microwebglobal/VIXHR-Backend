package com.microwebglobal.vixhr.company.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
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
@Table(name = "company_settings")
public class CompanySetting extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id",unique = true)
    private Company company;

    @NotNull
    @Column(columnDefinition = "jsonb")
    private String workingDays;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime standardWorkHoursStart;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime standardWorkHoursEnd;

    @NotNull
    private double regularHoursPerDay;

    @NotNull
    @Length(min = 2, max = 20)
    private String currencyCode;

    @NotNull
    @Length(min = 2, max = 20)
    private String dateFormat;

    @NotNull
    @Length(min = 2, max = 20)
    private String timeFormat;

    private boolean enableOvertime = true;

    private boolean enableGeoLocation = false;
}
