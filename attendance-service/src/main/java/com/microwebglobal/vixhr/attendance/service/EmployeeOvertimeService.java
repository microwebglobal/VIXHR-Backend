package com.microwebglobal.vixhr.attendance.service;

import com.microwebglobal.vixhr.attendance.client.CompanyClient;
import com.microwebglobal.vixhr.attendance.client.EmployeeClient;
import com.microwebglobal.vixhr.attendance.dto.DataRequest;
import com.microwebglobal.vixhr.attendance.dto.OvertimeClockRequest;
import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import com.microwebglobal.vixhr.attendance.repository.EmployeeOvertimeRepository;
import com.microwebglobal.vixhr.attendance.repository.OvertimeClockEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class EmployeeOvertimeService {

    private final CompanyClient companyClient;
    private final EmployeeClient employeeClient;
    private final OvertimeClockEventRepository overtimeClockEventRepository;
    private final EmployeeOvertimeRepository employeeOvertimeRepository;

    public Page<EmployeeOvertime> getOvertimeRecordsByEmployeeId(DataRequest request) {
        return employeeOvertimeRepository.findAllByEmployeeIdAndDateBetween(
                request.getEmployeeId(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPageable()
        );
    }

    public Page<OvertimeClockEvent> getOvertimeClockEventsByEmployeeId(DataRequest request) {
        return overtimeClockEventRepository.findAllByEmployeeIdAndDateBetween(
                request.getEmployeeId(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPageable()
        );
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
        var employeeResponse = employeeClient.getEmployeeById(request.getEmployeeId());
        if (employeeResponse == null || !employeeResponse.userId().equals(request.getSubmittedBy())) {
            assert employeeResponse != null;
            throw new RuntimeException("Invalid clock-in request for employee" + employeeResponse.id());
        }

//        var overtimePolicyResponse = companyClient.getOvertimePolicyByCompany(employeeResponse.companyId());
//        if (overtimePolicyResponse == null) {
//            throw new RuntimeException("Overtime policy not found");
//        }
//
//        if (overtimePolicyResponse.getStartTime().isAfter(LocalTime.now())) {
//            throw new RuntimeException("Cannot clock in before: " + overtimePolicyResponse.getStartTime());
//        }
//
//        if (overtimePolicyResponse.getEndTime().isBefore(LocalTime.now())) {
//            throw new RuntimeException("Cannot clock in after: " + overtimePolicyResponse.getEndTime());
//        }

        var clockEvent = OvertimeClockEvent.builder()
                .employeeId(request.getEmployeeId())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .eventTime(LocalTime.now())
                .date(LocalDate.now())
                .notes(request.getNotes())
                .eventType("IN")
                .build();

        overtimeClockEventRepository.save(clockEvent);
    }

    public void clockOut(OvertimeClockRequest request) {
        var employeeResponse = employeeClient.getEmployeeById(request.getEmployeeId());
        if (employeeResponse == null || !employeeResponse.userId().equals(request.getSubmittedBy())) {
            assert employeeResponse != null;
            throw new RuntimeException("Invalid clock-out request for employee" + employeeResponse.id());
        }

//        var overtimePolicyResponse = companyClient.getOvertimePolicyByCompany(employeeResponse.companyId());
//        if (overtimePolicyResponse == null) {
//            throw new RuntimeException("Overtime policy not found");
//        }
//
//        if (overtimePolicyResponse.getStartTime().isAfter(LocalTime.now())) {
//            throw new RuntimeException("Invalid clock-out attempt");
//        }

        var clockEvent = OvertimeClockEvent.builder()
                .employeeId(request.getEmployeeId())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .eventTime(LocalTime.now())
                .date(LocalDate.now())
                .notes(request.getNotes())
                .eventType("OUT")
                .build();

        overtimeClockEventRepository.save(clockEvent);
    }
}
