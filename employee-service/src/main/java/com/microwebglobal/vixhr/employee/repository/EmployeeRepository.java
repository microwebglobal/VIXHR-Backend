package com.microwebglobal.vixhr.employee.repository;

import com.microwebglobal.vixhr.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUserId(Long userId);

    @Query("SELECT DISTINCT e.companyId FROM Employee e")
    List<Long> findDistinctCompanyIds();

    @Query("SELECT e.id FROM Employee e WHERE e.companyId = :companyId AND e.terminationDate IS NULL")
    List<Long> findActiveEmployeeIdsByCompanyId(@Param("companyId") Long companyId);
}