package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeOvertimeRepository extends JpaRepository<EmployeeOvertime, Long> {
}