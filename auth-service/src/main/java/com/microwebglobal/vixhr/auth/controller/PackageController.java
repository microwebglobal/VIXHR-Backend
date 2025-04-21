package com.microwebglobal.vixhr.auth.controller;

import com.microwebglobal.vixhr.auth.model.Feature;
import com.microwebglobal.vixhr.auth.model.Package;
import com.microwebglobal.vixhr.auth.service.PackageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/packages")
@SecurityRequirement(name = "oauth")
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public Iterable<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PostMapping
    public Package createPackage(@RequestBody @Valid Package packageType) {
        return packageService.createPackage(packageType);
    }

    @PutMapping("/{id}")
    public Package updatePackage(@PathVariable Long id, @RequestBody @Valid Package packageType) {
        return packageService.updatePackage(id, packageType);
    }

    @DeleteMapping("/{id}")
    public void deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
    }

    @PostMapping("/features/{packageId}/{featureId}")
    public void addFeatureToPackage(@PathVariable Long packageId, @PathVariable Long featureId) {
        packageService.addFeatureToPackage(packageId, featureId);
    }

    @DeleteMapping("/features/{packageId}/{featureId}")
    public void removeFeatureFromPackage(@PathVariable Long packageId, @PathVariable Long featureId) {
        packageService.removeFeatureFromPackage(packageId, featureId);
    }

    @PostMapping("/features")
    public Feature createFeature(@RequestBody @Valid Feature feature) {
        return packageService.createFeature(feature);
    }

    @PutMapping("/features/{id}")
    public Feature updateFeature(@PathVariable Long id, @RequestBody @Valid Feature feature) {
        return packageService.updateFeature(id, feature);
    }

    @DeleteMapping("/features/{id}")
    public void deleteFeature(@PathVariable Long id) {
        packageService.deleteFeature(id);
    }
}
