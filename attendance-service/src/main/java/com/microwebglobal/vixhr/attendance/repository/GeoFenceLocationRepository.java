package com.microwebglobal.vixhr.attendance.repository;

import com.microwebglobal.vixhr.attendance.model.GeoFenceLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoFenceLocationRepository extends JpaRepository<GeoFenceLocation, Long> {

    Iterable<GeoFenceLocation> findAllByCompanyId(Long companyId);
}