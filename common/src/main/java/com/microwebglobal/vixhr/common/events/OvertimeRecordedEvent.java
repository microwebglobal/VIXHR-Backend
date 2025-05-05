package com.microwebglobal.vixhr.common.events;

import java.time.LocalDate;

public record OvertimeRecordedEvent(
        Long overtimeId,
        Long employeeId,
        Long companyId,
        LocalDate date,
        double hours,
        double rate,
        String status,
        String notes
) {}
