package com.microwebglobal.vixhr.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OvertimeClockRequest extends AttendanceRequest {

    @Length(min = 2, max = 500)
    private String notes;
}
