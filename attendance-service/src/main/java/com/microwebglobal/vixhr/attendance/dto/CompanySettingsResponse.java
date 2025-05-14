package com.microwebglobal.vixhr.attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySettingsResponse {

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime standardWorkHoursStart;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime standardWorkHoursEnd;

    private double regularHoursPerDay;
    private String timeFormat;
    private boolean enableGeoLocation = false;
}
