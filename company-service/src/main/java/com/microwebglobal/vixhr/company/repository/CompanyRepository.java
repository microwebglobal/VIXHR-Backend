package com.microwebglobal.vixhr.company.repository;

import com.microwebglobal.vixhr.company.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}