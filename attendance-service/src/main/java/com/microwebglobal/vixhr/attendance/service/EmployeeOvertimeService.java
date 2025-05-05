package com.microwebglobal.vixhr.attendance.service;

import com.microwebglobal.vixhr.attendance.client.EmployeeClient;
import com.microwebglobal.vixhr.attendance.dto.OvertimeClockRequest;
import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import com.microwebglobal.vixhr.attendance.repository.EmployeeOvertimeRepository;
import com.microwebglobal.vixhr.attendance.repository.OvertimeClockEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeOvertimeService {

    private final EmployeeClient employeeClient;
    private final OvertimeClockEventRepository overtimeClockEventRepository;
    private final EmployeeOvertimeRepository employeeOvertimeRepository;

    public List<EmployeeOvertime> getOvertimeRecordsByCompanyId(
            Long companyId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return employeeOvertimeRepository.findAllByCompanyIdAndDateBetween(companyId, startDate, endDate);
    }

    public List<EmployeeOvertime> getOvertimeRecordsByEmployeeId(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate) {
        return employeeOvertimeRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }

    public List<OvertimeClockEvent> getOvertimeClockEventsByEmployeeId(Long employeeId) {
        return overtimeClockEventRepository.findAllByEmployeeId(employeeId);
    }

    public EmployeeOvertime getOvertimeRecordById(Long id) {
        return employeeOvertimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Overtime record not found for ID: " + id));
    }

    public EmployeeOvertime createOvertimeRecord(EmployeeOvertime employeeOvertime) {
        return employeeOvertimeRepository.save(employeeOvertime);
    }

    public EmployeeOvertime updateOvertimeRecord(Long id, EmployeeOvertime employeeOvertime) {
        employeeOvertimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Overtime record not found for ID: " + id));

        employeeOvertime.setId(id);
        return employeeOvertimeRepository.save(employeeOvertime);
    }

    public void deleteOvertimeRecord(Long id) {
        var overtimeRecord = employeeOvertimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Overtime record not found for ID: " + id));

        employeeOvertimeRepository.delete(overtimeRecord);
    }

    public void clockIn(OvertimeClockRequest request) {
        var response = employeeClient.getEmployeeById(request.getEmployeeId());
        if (response == null || !response.userId().equals(request.getSubmittedBy())) {
            assert response != null;
            throw new RuntimeException("Invalid clock-in request for employee" + response.id());
        }

        var clockEvent = OvertimeClockEvent.builder()
                .employeeId(request.getEmployeeId())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .eventTime(LocalTime.now())
                .notes(request.getNotes())
                .eventType("IN")
                .build();

        overtimeClockEventRepository.save(clockEvent);
    }

    public void clockOut(OvertimeClockRequest request) {
        var response = employeeClient.getEmployeeById(request.getEmployeeId());
        if (response == null || !response.userId().equals(request.getSubmittedBy())) {
            assert response != null;
            throw new RuntimeException("Invalid clock-out request for employee" + response.id());
        }

        var clockEvent = OvertimeClockEvent.builder()
                .employeeId(request.getEmployeeId())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .eventTime(LocalTime.now())
                .notes(request.getNotes())
                .eventType("OUT")
                .build();

        overtimeClockEventRepository.save(clockEvent);
    }
}
