package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OvertimeClockEventRepository extends JpaRepository<OvertimeClockEvent, Long> {

    Page<OvertimeClockEvent> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate dateAfter, LocalDate dateBefore,
                                                               Pageable pageable);
}