package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    Page<AttendanceRecord> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate,
                                                             Pageable pageable);

    Optional<AttendanceRecord> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}