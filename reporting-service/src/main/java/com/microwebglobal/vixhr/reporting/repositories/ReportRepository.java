package com.microwebglobal.vixhr.reporting.repositories;

import com.microwebglobal.vixhr.reporting.models.AttendanceReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<AttendanceReport, Long> {

    List<AttendanceReport> findAllByEmployeeId(Long employeeId);

    Optional<AttendanceReport> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}

