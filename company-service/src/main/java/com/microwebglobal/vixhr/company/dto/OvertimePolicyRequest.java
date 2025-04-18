package com.microwebglobal.vixhr.company.dto;

import com.microwebglobal.vixhr.company.models.OvertimePolicy;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OvertimePolicyRequest {

    @NotNull
    @Positive
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double rateMultiplier;

    @NotNull
    private Time startTime;

    @NotNull
    private Time endTime;

    private String applicableDays;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerDay;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerWeek;

    @DecimalMin(value = "0.0", inclusive = false)
    private double maxHoursPerMonth;

    private boolean isHoliday = false;

    private boolean requiresApproval = true;

    public OvertimePolicy toOvertimePolicy() {
        OvertimePolicy overtimePolicy = new OvertimePolicy();
        overtimePolicy.setName(name);
        overtimePolicy.setRateMultiplier(rateMultiplier);
        overtimePolicy.setStartTime(startTime);
        overtimePolicy.setEndTime(endTime);
        overtimePolicy.setApplicableDays(applicableDays);
        overtimePolicy.setMaxHoursPerDay(maxHoursPerDay);
        overtimePolicy.setMaxHoursPerWeek(maxHoursPerWeek);
        overtimePolicy.setMaxHoursPerMonth(maxHoursPerMonth);
        overtimePolicy.setHoliday(isHoliday);
        overtimePolicy.setRequiresApproval(requiresApproval);
        return overtimePolicy;
    }
}
