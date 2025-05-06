package com.microwebglobal.vixhr.reporting.repositories;

import com.microwebglobal.vixhr.reporting.models.AttendanceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    Page<AttendanceRecord> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<AttendanceRecord> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<AttendanceRecord> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate dateAfter, LocalDate dateBefore);

    List<AttendanceRecord> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate dateAfter, LocalDate dateBefore);

    Optional<AttendanceRecord> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    void deleteAllByEmployeeId(Long employeeId);

    void deleteAllByCompanyId(Long companyId);
}

