package com.microwebglobal.vixhr.attendance.controller;

import com.microwebglobal.vixhr.attendance.dto.AttendanceRequest;
import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import com.microwebglobal.vixhr.attendance.service.AttendanceRecordService;
import com.microwebglobal.vixhr.attendance.util.AttendanceReportGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
@SecurityRequirement(name = "oauth")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceRecordService;
    private final AttendanceReportGenerator attendanceReportGenerator;

    @GetMapping("/export")
    public void exportAttendance(
            HttpServletResponse response,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(defaultValue = "excel") String format,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) throws IOException {
        List<AttendanceRecord> records = companyId != null
                ? attendanceRecordService.getAttendanceByCompanyId(companyId, startDate, endDate)
                : attendanceRecordService.getAttendanceByEmployeeId(employeeId, startDate, endDate);

        if (records.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        if (format.equalsIgnoreCase("csv")) {
            attendanceReportGenerator.exportToCsv(records, response);
        } else {
            attendanceReportGenerator.exportToExcel(records, response);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<AttendanceRecord> getAttendanceByEmployee(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        return attendanceRecordService.getAttendanceByEmployeeId(employeeId, startDate, endDate);
    }

    @GetMapping("/{id}")
    public AttendanceRecord getAttendanceRecordById(@PathVariable Long id) {
        return attendanceRecordService.getAttendanceRecordById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttendanceRecord createAttendanceRecord(@RequestBody @Valid AttendanceRecord attendanceRecord) {
        return attendanceRecordService.createAttendanceRecord(attendanceRecord);
    }

    @PutMapping("/{id}")
    public AttendanceRecord updateAttendanceRecord(
            @PathVariable Long id,
            @RequestBody @Valid AttendanceRecord attendanceRecord
    ) {
        return attendanceRecordService.updateAttendanceRecord(id, attendanceRecord);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendanceRecord(@PathVariable Long id) {
        attendanceRecordService.deleteAttendanceRecord(id);
    }

    @PostMapping("/clock-in")
    @ResponseStatus(HttpStatus.CREATED)
    public void clockIn (
            @AuthenticationPrincipal Jwt token,
            HttpServletRequest httpRequest,
            @RequestBody AttendanceRequest request,
            @RequestHeader("User-Agent") String deviceId
    ) {
        request.setDeviceId(deviceId);
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setSubmittedBy(token.getClaimAsString("userId"));
        attendanceRecordService.clockIn(request);
    }

    @PostMapping("/clock-out")
    @ResponseStatus(HttpStatus.CREATED)
    public void clockOut (
            @AuthenticationPrincipal Jwt token,
            HttpServletRequest httpRequest,
            @RequestBody AttendanceRequest request,
            @RequestHeader("User-Agent") String deviceId
    ) {
        request.setDeviceId(deviceId);
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setSubmittedBy(token.getClaimAsString("userId"));
        attendanceRecordService.clockOut(request);
    }
}
