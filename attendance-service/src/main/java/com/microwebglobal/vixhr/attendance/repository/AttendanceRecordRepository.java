package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

    Optional<AttendanceRecord> findByEmployeeIdAndDate(Long employeeId, LocalDate date);
}