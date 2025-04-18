package com.microwebglobal.vixhr.company.dto;

import com.microwebglobal.vixhr.company.models.CompanySetting;
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
@NoArgsConstructor
@AllArgsConstructor
public class CompanySettingRequest {

    @NotNull
    @Positive
    private Long companyId;

    @NotNull
    private String workingDays;

    @NotNull
    private Time standardWorkHoursStart;

    @NotNull
    private Time standardWorkHoursEnd;

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

    public CompanySetting toCompanySetting() {
        CompanySetting companySetting = new CompanySetting();
        companySetting.setWorkingDays(workingDays);
        companySetting.setStandardWorkHoursStart(standardWorkHoursStart);
        companySetting.setStandardWorkHoursEnd(standardWorkHoursEnd);
        companySetting.setRegularHoursPerDay(regularHoursPerDay);
        companySetting.setCurrencyCode(currencyCode);
        companySetting.setDateFormat(dateFormat);
        companySetting.setTimeFormat(timeFormat);
        companySetting.setEnableOvertime(enableOvertime);
        companySetting.setEnableGeoLocation(enableGeoLocation);
        return companySetting;
    }
}
