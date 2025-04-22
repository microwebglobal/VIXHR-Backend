package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OvertimeClockEventRepository extends JpaRepository<OvertimeClockEvent, Long> {

    List<OvertimeClockEvent> findAllByEmployeeId(Long employeeId);
}