package com.microwebglobal.vixhr.employee.repository;

import com.microwebglobal.vixhr.employee.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
}