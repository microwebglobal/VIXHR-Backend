package com.microwebglobal.vixhr.reporting.messaging;

import com.microwebglobal.vixhr.common.events.AttendanceRecordedEvent;
import com.microwebglobal.vixhr.common.events.OvertimeRecordedEvent;
import com.microwebglobal.vixhr.reporting.models.AttendanceRecord;
import com.microwebglobal.vixhr.reporting.repositories.AttendanceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportConsumer {

    private final AttendanceRecordRepository repository;

    @KafkaListener(topics="attendance.recorded", containerFactory="kafkaListenerContainerFactory")
    public void onAttendance(AttendanceRecordedEvent ev) {
        AttendanceRecord report = AttendanceRecord.builder()
                .attendanceId(ev.attendanceId())
                .employeeId(ev.employeeId())
                .companyId(ev.companyId())
                .employeeCode(ev.employeeCode())
                .fullName(ev.fullName())
                .department(ev.department())
                .jobRole(ev.jobRole())
                .baseSalary(ev.baseSalary())
                .date(ev.date())
                .checkInTime(ev.checkInTime())
                .checkOutTime(ev.checkOutTime())
                .status(ev.status())
                .notes(ev.notes())
                .isOvertime(false)
                .build();

        repository.save(report);
    }

    @KafkaListener(topics="overtime.recorded", containerFactory="kafkaListenerContainerFactory")
    public void onOvertime(OvertimeRecordedEvent ev) {
        repository.findByEmployeeIdAndDate(ev.employeeId(), ev.date())
                .ifPresent(r -> {
                    r.setIsOvertime(true);
                    r.setOvertimeHours(ev.hours());
                    r.setOvertimeRate(ev.rate());
                    r.setOvertimeStatus(ev.status());
                    r.setNotes(ev.notes());
                    repository.save(r);
                });
    }
}

