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
        geoFenceLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeoFenceLocation not found"));

        geoFenceLocation.setId(id);
        return geoFenceLocationRepository.save(geoFenceLocation);
    }

    public void deleteGeoFenceLocation(Long id) {
        var geoFenceLocation = geoFenceLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GeoFenceLocation not found"));
        geoFenceLocationRepository.delete(geoFenceLocation);
    }
}
