package com.microwebglobal.vixhr.auth.repository;

import com.microwebglobal.vixhr.auth.model.Feature;
import com.microwebglobal.vixhr.auth.model.Package;
import com.microwebglobal.vixhr.auth.model.PackageFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageFeatureRepository extends JpaRepository<PackageFeature, Long> {

    PackageFeature findByPackageTypeAndFeature(Package packageType, Feature feature);
}