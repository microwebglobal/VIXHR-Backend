package com.microwebglobal.vixhr.company.repository;

import com.microwebglobal.vixhr.company.models.OvertimePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimePolicyRepository extends JpaRepository<OvertimePolicy, Long> {

    Iterable<OvertimePolicy> findAllByCompanyId(Long companyId);
}