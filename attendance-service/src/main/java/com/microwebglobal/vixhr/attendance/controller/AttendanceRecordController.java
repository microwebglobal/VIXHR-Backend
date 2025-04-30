package com.microwebglobal.vixhr.attendance.controller;

import com.microwebglobal.vixhr.attendance.dto.AttendanceRequest;
import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import com.microwebglobal.vixhr.attendance.service.AttendanceRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
@SecurityRequirement(name = "oauth")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceRecordService;

    @GetMapping("/employee/{employeeId}")
    public List<AttendanceRecord> getAttendanceByEmployee(@PathVariable Long employeeId) {
        return attendanceRecordService.getAttendanceByEmployeeId(employeeId);
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
