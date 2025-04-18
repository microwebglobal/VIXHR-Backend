package com.microwebglobal.vixhr.company.repository;

import com.microwebglobal.vixhr.company.models.CompanySetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanySettingRepository extends JpaRepository<CompanySetting, Long> {

    Iterable<CompanySetting> findAllByCompanyId(Long companyId);
}