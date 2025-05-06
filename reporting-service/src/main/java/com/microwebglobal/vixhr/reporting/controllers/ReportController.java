package com.microwebglobal.vixhr.reporting.controllers;

import com.microwebglobal.vixhr.reporting.dto.AttendanceReportResponse;
import com.microwebglobal.vixhr.reporting.models.AttendanceReport;
import com.microwebglobal.vixhr.reporting.services.AttendanceReportService;
import com.microwebglobal.vixhr.reporting.util.ReportGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@SecurityRequirement(name = "oauth")
public class ReportController {

    private final ReportGenerator reportGenerator;
    private final AttendanceReportService reportService;

    @GetMapping("/export")
    public ResponseEntity<Resource> exportAttendanceReports(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) throws IOException {
        List<AttendanceReport> reports = companyId != null
                ? reportService.getAllByCompanyId(companyId, startDate, endDate)
                : reportService.getAllByEmployeeId(employeeId, startDate, endDate);

        ByteArrayInputStream in = reportGenerator.exportToExcel(reports);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=attendance_reports.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/company")
    public ResponseEntity<Page<AttendanceReportResponse>> getAllByCompanyId(
            @RequestParam Long companyId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        var result = reportService.getPaginatedByCompanyId(companyId, startDate, endDate, pageable);
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
        var result = reportService.getPaginatedByEmployeeId(employeeId, startDate, endDate, pageable);
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
