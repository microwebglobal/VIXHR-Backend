package com.microwebglobal.vixhr.attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OvertimePolicyResponse {

    private double rateMultiplier;
    private String applicableDays;
    private double maxHoursPerDay;
    private double maxHoursPerWeek;
    private double maxHoursPerMonth;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
}
