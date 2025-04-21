package com.microwebglobal.vixhr.attendance.service;

import com.microwebglobal.vixhr.attendance.model.GeoFenceLocation;
import com.microwebglobal.vixhr.attendance.repository.GeoFenceLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeoFenceLocationService {

    private final GeoFenceLocationRepository geoFenceLocationRepository;

    public Iterable<GeoFenceLocation> getAllGeoFenceLocationsByCompanyId(Long companyId) {
        return geoFenceLocationRepository.findAllByCompanyId(companyId);
    }

    public GeoFenceLocation getGeoFenceLocationById(Long id) {
        return geoFenceLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeoFenceLocation not found"));
    }

    public GeoFenceLocation createGeoFenceLocation(GeoFenceLocation geoFenceLocation) {
        return geoFenceLocationRepository.save(geoFenceLocation);
    }

    public GeoFenceLocation updateGeoFenceLocation(Long id, GeoFenceLocation geoFenceLocation) {
        GeoFenceLocation existingGeoFenceLocation = geoFenceLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeoFenceLocation not found"));

        existingGeoFenceLocation.setName(geoFenceLocation.getName());
        existingGeoFenceLocation.setLatitude(geoFenceLocation.getLatitude());
        existingGeoFenceLocation.setLongitude(geoFenceLocation.getLongitude());
        existingGeoFenceLocation.setRadius(geoFenceLocation.getRadius());
        existingGeoFenceLocation.setAddress(geoFenceLocation.getAddress());
        return geoFenceLocationRepository.save(existingGeoFenceLocation);
    }

    public void deleteGeoFenceLocation(Long id) {
        var geoFenceLocation = geoFenceLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeoFenceLocation not found"));
        geoFenceLocationRepository.delete(geoFenceLocation);
    }
}
