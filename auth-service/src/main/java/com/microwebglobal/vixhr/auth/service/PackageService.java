package com.microwebglobal.vixhr.auth.service;

import com.microwebglobal.vixhr.auth.model.Feature;
import com.microwebglobal.vixhr.auth.model.Package;
import com.microwebglobal.vixhr.auth.model.PackageFeature;
import com.microwebglobal.vixhr.auth.repository.FeatureRepository;
import com.microwebglobal.vixhr.auth.repository.PackageFeatureRepository;
import com.microwebglobal.vixhr.auth.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;
    private final FeatureRepository featureRepository;
    private final PackageFeatureRepository packageFeatureRepository;

    public Iterable<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Package createPackage(Package packageType) {
        return packageRepository.save(packageType);
    }

    public Package updatePackage(Long id, Package packageType) {
        var existingPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + id));

        existingPackage.setName(packageType.getName());
        existingPackage.setDescription(packageType.getDescription());
        existingPackage.setBasePrice(packageType.getBasePrice());
        existingPackage.setMaxEmployees(packageType.getMaxEmployees());

        return packageRepository.save(existingPackage);
    }

    public void deletePackage(Long id) {
        var packageType = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + id));

        packageRepository.delete(packageType);
    }

    public void addFeatureToPackage(Long packageId, Long featureId) {
        var packageType = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + packageId));

        var feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + featureId));

        var packageFeature = new PackageFeature();
        packageFeature.setPackageType(packageType);
        packageFeature.setFeature(feature);

        packageFeatureRepository.save(packageFeature);
    }

    public void addFeaturesToPackage(Long packageId, Iterable<Long> featureIds) {
        var packageType = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + packageId));

        for (Long featureId : featureIds) {
            var feature = featureRepository.findById(featureId)
                    .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + featureId));

            var packageFeature = new PackageFeature();
            packageFeature.setPackageType(packageType);
            packageFeature.setFeature(feature);

            packageFeatureRepository.save(packageFeature);
        }
    }

    public void removeFeatureFromPackage(Long packageId, Long featureId) {
        var packageType = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + packageId));

        var feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + featureId));

        var packageFeature = packageFeatureRepository.findByPackageTypeAndFeature(packageType, feature);
        packageFeatureRepository.delete(packageFeature);
    }

    public void removeFeaturesFromPackage(Long packageId, Iterable<Long> featureIds) {
        var packageType = packageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + packageId));

        for (Long featureId : featureIds) {
            var feature = featureRepository.findById(featureId)
                    .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + featureId));

            var packageFeature = packageFeatureRepository.findByPackageTypeAndFeature(packageType, feature);
            packageFeatureRepository.delete(packageFeature);
        }
    }

    public Iterable<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Feature createFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    public Feature updateFeature(Long id, Feature feature) {
        var existingFeature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + id));

        existingFeature.setName(feature.getName());
        existingFeature.setCode(feature.getCode());
        existingFeature.setDescription(feature.getDescription());
        existingFeature.setModule(feature.getModule());

        return featureRepository.save(existingFeature);
    }

    public void deleteFeature(Long id) {
        var feature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found for ID: " + id));

        featureRepository.delete(feature);
    }
}
