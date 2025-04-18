package com.microwebglobal.vixhr.company.controllers;

import com.microwebglobal.vixhr.company.dto.CompanySettingRequest;
import com.microwebglobal.vixhr.company.models.CompanySetting;
import com.microwebglobal.vixhr.company.services.CompanySettingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company-settings")
@SecurityRequirement(name = "oauth")
public class CompanySettingController {

    private final CompanySettingService companySettingService;

    @GetMapping("/company/{companyId}")
    public Iterable<CompanySetting> getSettingsByCompany(@PathVariable Long companyId) {
        return companySettingService.getSettingsByCompany(companyId);
    }

    @PostMapping
    public CompanySetting createCompanySetting(@RequestBody @Valid CompanySettingRequest request) {
        return companySettingService.save(request);
    }

    @PutMapping("/{id}")
    public CompanySetting updateCompanySetting(@PathVariable Long id, @RequestBody @Valid CompanySettingRequest request) {
        return companySettingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanySetting(@PathVariable Long id) {
        companySettingService.delete(id);
    }
}
