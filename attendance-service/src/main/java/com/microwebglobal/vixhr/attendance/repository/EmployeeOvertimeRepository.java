package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.EmployeeOvertime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeOvertimeRepository extends JpaRepository<EmployeeOvertime, Long> {

    List<EmployeeOvertime> findAllByCompanyIdAndDateBetween(Long companyId, LocalDate startDate, LocalDate endDate);

    List<EmployeeOvertime> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
}