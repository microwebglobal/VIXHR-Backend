package com.microwebglobal.vixhr.auth.repository;

import com.microwebglobal.vixhr.auth.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}