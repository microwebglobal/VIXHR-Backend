package com.microwebglobal.vixhr.reporting.repositories;

import com.microwebglobal.vixhr.reporting.models.AttendanceReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<AttendanceReport, Long> {

    Page<AttendanceReport> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<AttendanceReport> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<AttendanceReport> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate dateAfter, LocalDate dateBefore);

    List<AttendanceReport> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate dateAfter, LocalDate dateBefore);

    Optional<AttendanceReport> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    void deleteAllByEmployeeId(Long employeeId);

    void deleteAllByCompanyId(Long companyId);
}

