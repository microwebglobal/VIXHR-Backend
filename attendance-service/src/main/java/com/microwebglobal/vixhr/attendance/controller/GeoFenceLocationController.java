package com.microwebglobal.vixhr.attendance.controller;

import com.microwebglobal.vixhr.attendance.model.GeoFenceLocation;
import com.microwebglobal.vixhr.attendance.service.GeoFenceLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/geo-fence-locations")
public class GeoFenceLocationController {

    private final GeoFenceLocationService geoFenceLocationService;

    @GetMapping("/company/{companyId}")
    public Iterable<GeoFenceLocation> getAllGeoFenceLocations(@PathVariable Long companyId) {
        return geoFenceLocationService.getAllGeoFenceLocationsByCompanyId(companyId);
    }

    @GetMapping("/{id}")
    public GeoFenceLocation getGeoFenceLocationById(@PathVariable Long id) {
        return geoFenceLocationService.getGeoFenceLocationById(id);
    }

    @PostMapping
    public GeoFenceLocation createGeoFenceLocation(@RequestBody @Valid GeoFenceLocation geoFenceLocation) {
        return geoFenceLocationService.createGeoFenceLocation(geoFenceLocation);
    }

    @PutMapping("/{id}")
    public GeoFenceLocation updateGeoFenceLocation(
            @PathVariable Long id,
            @RequestBody @Valid GeoFenceLocation geoFenceLocation
    ) {
        return geoFenceLocationService.updateGeoFenceLocation(id, geoFenceLocation);
    }

    @DeleteMapping("/{id}")
    public void deleteGeoFenceLocation(@PathVariable Long id) {
        geoFenceLocationService.deleteGeoFenceLocation(id);
    }
}
