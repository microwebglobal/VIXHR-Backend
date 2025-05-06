package com.microwebglobal.vixhr.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationEvent {

    private List<Long> employeeIds;
    private Long companyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String source; // scheduled or manual
}

