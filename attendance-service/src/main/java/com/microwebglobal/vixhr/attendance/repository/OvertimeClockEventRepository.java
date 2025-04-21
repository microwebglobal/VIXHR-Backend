package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.OvertimeClockEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimeClockEventRepository extends JpaRepository<OvertimeClockEvent, Long> {
}