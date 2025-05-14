package com.microwebglobal.vixhr.attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "overtime_clock_events")
public class OvertimeClockEvent extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long overtimePolicyId;

    @NotNull
    @Length(min = 2, max = 10)
    private String eventType;

    @NotNull
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime eventTime;

    private double latitude;
    private double longitude;

    @Length(min = 2, max = 100)
    private String address;

    @Length(min = 2, max = 100)
    private String deviceId;

    @Length(min = 45, max = 45)
    private String ipAddress;

    private boolean locationVerified;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "geo_fence_location_id")
    private GeoFenceLocation geoFenceLocation;

    @Length(min = 2, max = 500)
    private String notes;
}
