package com.microwebglobal.vixhr.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataRequest {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Pageable pageable;
}
