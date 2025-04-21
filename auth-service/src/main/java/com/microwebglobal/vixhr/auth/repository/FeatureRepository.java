package com.microwebglobal.vixhr.auth.repository;

import com.microwebglobal.vixhr.auth.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
}