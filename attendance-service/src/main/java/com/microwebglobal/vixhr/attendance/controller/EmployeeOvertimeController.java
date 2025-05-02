package com.microwebglobal.vixhr.attendance.controller;

import com.microwebglobal.vixhr.attendance.dto.OvertimeClockRequest;
import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import com.microwebglobal.vixhr.attendance.service.EmployeeOvertimeService;
import com.microwebglobal.vixhr.attendance.util.OvertimeReportGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/overtime")
@SecurityRequirement(name = "oauth")
public class EmployeeOvertimeController {

    private final OvertimeReportGenerator reportGenerator;
    private final EmployeeOvertimeService employeeOvertimeService;

    @GetMapping("/export")
    public void exportAttendance(
            HttpServletResponse response,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(defaultValue = "excel") String format,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) throws IOException {
        List<EmployeeOvertime> records = companyId != null
                ? employeeOvertimeService.getOvertimeRecordsByCompanyId(companyId, startDate, endDate)
                : employeeOvertimeService.getOvertimeRecordsByEmployeeId(employeeId, startDate, endDate);

        if (records.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        if (format.equalsIgnoreCase("csv")) {
            reportGenerator.exportToCsv(records, response);
        } else {
            reportGenerator.exportToExcel(records, response);
        }
    }

    @GetMapping("/events/employee/{employeeId}")
    public List<OvertimeClockEvent> getOvertimeClockEventsByEmployeeId(@PathVariable Long employeeId) {
        return employeeOvertimeService.getOvertimeClockEventsByEmployeeId(employeeId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeOvertime> getOvertimeRecordsByEmployeeId(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return employeeOvertimeService.getOvertimeRecordsByEmployeeId(employeeId, startDate, endDate);
    }

    @GetMapping("/{id}")
    public EmployeeOvertime getOvertimeRecordById(@PathVariable Long id) {
        return employeeOvertimeService.getOvertimeRecordById(id);
    }

    @PostMapping
    public EmployeeOvertime createOvertimeRecord(@RequestBody @Valid EmployeeOvertime employeeOvertime) {
        return employeeOvertimeService.createOvertimeRecord(employeeOvertime);
    }

    @PutMapping("/{id}")
    public EmployeeOvertime updateOvertimeRecord(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeOvertime employeeOvertime
    ) {
        return employeeOvertimeService.updateOvertimeRecord(id, employeeOvertime);
    }

    @DeleteMapping("/{id}")
    public void deleteOvertimeRecord(@PathVariable Long id) {
        employeeOvertimeService.deleteOvertimeRecord(id);
    }

    @PostMapping("/clock-in")
    public void clockIn (
            HttpServletRequest httpRequest,
            @AuthenticationPrincipal Jwt token,
            @RequestBody OvertimeClockRequest request,
            @RequestHeader("User-Agent") String deviceId
    ) {
        request.setDeviceId(deviceId);
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setSubmittedBy(token.getClaimAsString("userId"));
        employeeOvertimeService.clockIn(request);
    }

    @PostMapping("/clock-out")
    public void clockOut (
            HttpServletRequest httpRequest,
            @AuthenticationPrincipal Jwt token,
            @RequestBody OvertimeClockRequest request,
            @RequestHeader("User-Agent") String deviceId
    ) {
        request.setDeviceId(deviceId);
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setSubmittedBy(token.getClaimAsString("userId"));
        employeeOvertimeService.clockOut(request);
    }
}
