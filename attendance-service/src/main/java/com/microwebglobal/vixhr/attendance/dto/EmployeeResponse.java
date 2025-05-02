package com.microwebglobal.vixhr.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private String id;
    private String userId;
    private Long companyId;
    private String firstName;
    private String lastName;
}
