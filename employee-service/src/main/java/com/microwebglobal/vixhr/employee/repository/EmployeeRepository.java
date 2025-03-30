package com.microwebglobal.vixhr.employee.repository;

import com.microwebglobal.vixhr.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUserId(Long userId);
}