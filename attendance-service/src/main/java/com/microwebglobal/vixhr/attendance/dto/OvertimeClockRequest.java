package com.microwebglobal.vixhr.attendance.dto;

import jakarta.validation.constraints.Positive;
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

    @Positive
    private Long overtimePolicyId;

    @Length(min = 2, max = 500)
    private String notes;
}
