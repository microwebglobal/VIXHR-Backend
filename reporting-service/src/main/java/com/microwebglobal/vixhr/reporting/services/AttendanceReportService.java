package com.microwebglobal.vixhr.reporting.services;

import com.microwebglobal.vixhr.reporting.dto.AttendanceReportResponse;
import com.microwebglobal.vixhr.reporting.repositories.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceReportService {

    private final ReportRepository reportRepository;

    public Page<AttendanceReportResponse> getAllByCompanyId(Long companyId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return reportRepository.findAllByCompanyIdAndDateBetween(companyId, startDate, endDate, pageable)
                .map(AttendanceReportResponse::from);
    }

    public Page<AttendanceReportResponse> getAllByEmployeeId(Long employeeId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return reportRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate, pageable)
                .map(AttendanceReportResponse::from);
    }

    public void deleteAllByEmployeeId(Long employeeId) {
        reportRepository.deleteAllByEmployeeId(employeeId);
    }

    public void deleteAllByCompanyId(Long companyId) {
        reportRepository.deleteAllByCompanyId(companyId);
    }

    public void deleteById(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new EntityNotFoundException("Attendance report not found");
        }
        reportRepository.deleteById(id);
    }
}
