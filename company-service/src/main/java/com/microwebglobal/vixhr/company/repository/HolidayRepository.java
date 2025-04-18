package com.microwebglobal.vixhr.company.repository;

import com.microwebglobal.vixhr.company.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Iterable<Holiday> findAllByCompanyId(Long companyId);
}