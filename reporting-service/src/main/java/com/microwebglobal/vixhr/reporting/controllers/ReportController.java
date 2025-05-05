package com.microwebglobal.vixhr.reporting.controllers;

import com.microwebglobal.vixhr.reporting.dto.AttendanceReportResponse;
import com.microwebglobal.vixhr.reporting.services.AttendanceReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@SecurityRequirement(name = "oauth")
public class ReportController {

    private final AttendanceReportService reportService;

    @GetMapping("/company")
    public ResponseEntity<Page<AttendanceReportResponse>> getAllByCompanyId(
            @RequestParam Long companyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        var result = reportService.getAllByCompanyId(companyId, startDate, endDate, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee")
    public ResponseEntity<Page<AttendanceReportResponse>> getAllByEmployeeId(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        var result = reportService.getAllByEmployeeId(employeeId, startDate, endDate, pageable);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Void> deleteAllByEmployeeId(@PathVariable Long employeeId) {
        reportService.deleteAllByEmployeeId(employeeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/company/{companyId}")
    public ResponseEntity<Void> deleteAllByCompanyId(@PathVariable Long companyId) {
        reportService.deleteAllByCompanyId(companyId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
