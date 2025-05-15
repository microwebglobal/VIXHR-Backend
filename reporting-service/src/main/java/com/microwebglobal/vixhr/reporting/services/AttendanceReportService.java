package com.microwebglobal.vixhr.reporting.services;

import com.microwebglobal.vixhr.reporting.dto.ReportDataRecordResponse;
import com.microwebglobal.vixhr.reporting.models.ReportDataRecord;
import com.microwebglobal.vixhr.reporting.repositories.ReportDataRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceReportService {

    private final ReportDataRepository reportDataRepository;

    public Page<ReportDataRecordResponse> getPaginatedByCompanyId(
            Long companyId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return reportDataRepository.findAllByCompanyIdAndDateBetween(companyId, startDate, endDate, pageable)
                .map(ReportDataRecordResponse::from);
    }

    public Page<ReportDataRecordResponse> getPaginatedByEmployeeId(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return reportDataRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate, pageable)
                .map(ReportDataRecordResponse::from);
    }

    public List<ReportDataRecord> getAllByCompanyId(Long companyId, LocalDate startDate, LocalDate endDate) {
        return reportDataRepository.findAllByCompanyIdAndDateBetweenAndStatusNot(
                companyId,
                startDate,
                endDate,
                "DELETED_EMPLOYEE"
        );
    }

    public List<ReportDataRecord> getAllByEmployeeId(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return reportDataRepository.findAllByEmployeeIdAndDateBetweenAndStatusNot(
                employeeId,
                startDate,
                endDate,
                "DELETED_EMPLOYEE"
        );
    }

    public void deleteAllByEmployeeId(Long employeeId) {
        reportDataRepository.deleteAllByEmployeeId(employeeId);
    }

    public void deleteAllByCompanyId(Long companyId) {
        reportDataRepository.deleteAllByCompanyId(companyId);
    }

    public void deleteById(Long id) {
        if (!reportDataRepository.existsById(id)) {
            throw new EntityNotFoundException("Attendance report not found");
        }
        reportDataRepository.deleteById(id);
    }
}
