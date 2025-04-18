package com.microwebglobal.vixhr.company.dto;

import com.microwebglobal.vixhr.company.models.Holiday;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HolidayRequest {

    @NotNull
    @Positive
    private Long companyId;

    @NotNull
    @Length(min = 2, max = 100)
    private String name;

    @NotNull
    private LocalDate date;

    private boolean isRecurring = false;

    @Length(min = 2, max = 100)
    private String description;

    public Holiday toHoliday() {
        Holiday holiday = new Holiday();
        holiday.setName(name);
        holiday.setDate(date);
        holiday.setRecurring(isRecurring);
        holiday.setDescription(description);
        return holiday;
    }
}
