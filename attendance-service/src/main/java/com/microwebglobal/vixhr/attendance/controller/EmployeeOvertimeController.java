package com.microwebglobal.vixhr.attendance.controller;

import com.microwebglobal.vixhr.attendance.dto.OvertimeClockRequest;
import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import com.microwebglobal.vixhr.attendance.service.EmployeeOvertimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/overtime")
@SecurityRequirement(name = "oauth")
public class EmployeeOvertimeController {

    private final EmployeeOvertimeService employeeOvertimeService;

    @GetMapping("/events/employee/{employeeId}")
    public List<OvertimeClockEvent> getOvertimeClockEventsByEmployeeId(@PathVariable Long employeeId) {
        return employeeOvertimeService.getOvertimeClockEventsByEmployeeId(employeeId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeOvertime> getOvertimeRecordsByEmployeeId(@PathVariable Long employeeId) {
        return employeeOvertimeService.getOvertimeRecordsByEmployeeId(employeeId);
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
            @RequestBody OvertimeClockRequest request,
            Principal principal
    ) {
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setDeviceId(httpRequest.getRemoteHost());
        request.setSubmittedBy(principal.getName());
        employeeOvertimeService.clockIn(request);
    }

    @PostMapping("/clock-out")
    public void clockOut (
            HttpServletRequest httpRequest,
            @RequestBody OvertimeClockRequest request,
            Principal principal
    ) {
        request.setIpAddress(httpRequest.getRemoteAddr());
        request.setDeviceId(httpRequest.getRemoteHost());
        request.setSubmittedBy(principal.getName());
        employeeOvertimeService.clockOut(request);
    }
}
