package com.microwebglobal.vixhr.attendance.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {

    private Long employeeId;

    private double latitude;

    private double longitude;

    @Length(min = 2, max = 100)
    private String address;

    @JsonIgnore
    private String deviceId;

    @JsonIgnore
    private String ipAddress;

    @JsonIgnore
    private String submittedBy;
}
