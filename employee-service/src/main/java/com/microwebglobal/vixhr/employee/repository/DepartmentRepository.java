package com.microwebglobal.vixhr.employee.repository;

import com.microwebglobal.vixhr.employee.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Iterable<Department> findAllByCompanyId(Long companyId);
}