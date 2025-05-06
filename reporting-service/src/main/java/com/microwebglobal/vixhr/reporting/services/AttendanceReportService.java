package com.microwebglobal.vixhr.reporting.services;

import com.microwebglobal.vixhr.reporting.dto.AttendanceRecordResponse;
import com.microwebglobal.vixhr.reporting.models.AttendanceRecord;
import com.microwebglobal.vixhr.reporting.repositories.AttendanceRecordRepository;
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

    private final AttendanceRecordRepository attendanceRecordRepository;

    public Page<AttendanceRecordResponse> getPaginatedByCompanyId(
            Long companyId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return attendanceRecordRepository.findAllByCompanyIdAndDateBetween(companyId, startDate, endDate, pageable)
                .map(AttendanceRecordResponse::from);
    }

    public Page<AttendanceRecordResponse> getPaginatedByEmployeeId(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return attendanceRecordRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate, pageable)
                .map(AttendanceRecordResponse::from);
    }

    public List<AttendanceRecord> getAllByCompanyId(Long companyId, LocalDate startDate, LocalDate endDate) {
        return attendanceRecordRepository.findAllByCompanyIdAndDateBetween(companyId, startDate, endDate);
    }

    public List<AttendanceRecord> getAllByEmployeeId(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRecordRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
    }

    public void deleteAllByEmployeeId(Long employeeId) {
        attendanceRecordRepository.deleteAllByEmployeeId(employeeId);
    }

    public void deleteAllByCompanyId(Long companyId) {
        attendanceRecordRepository.deleteAllByCompanyId(companyId);
    }

    public void deleteById(Long id) {
        if (!attendanceRecordRepository.existsById(id)) {
            throw new EntityNotFoundException("Attendance report not found");
        }
        attendanceRecordRepository.deleteById(id);
    }
}
