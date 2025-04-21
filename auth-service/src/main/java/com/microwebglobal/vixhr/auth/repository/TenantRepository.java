package com.microwebglobal.vixhr.auth.repository;

import com.microwebglobal.vixhr.auth.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}