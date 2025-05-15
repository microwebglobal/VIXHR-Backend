package com.microwebglobal.vixhr.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReconciliationEvent {

    private List<Long> employeeIds;
    private Long companyId;
    private String source; // scheduled or manual
}

