package com.microwebglobal.vixhr.company.controllers;

import com.microwebglobal.vixhr.company.dto.OvertimePolicyRequest;
import com.microwebglobal.vixhr.company.models.OvertimePolicy;
import com.microwebglobal.vixhr.company.services.OvertimePolicyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/overtime-policies")
@SecurityRequirement(name = "oauth")
public class OvertimePolicyController {

    private final OvertimePolicyService overtimePolicyService;

    @GetMapping("/company/{companyId}")
    public Iterable<OvertimePolicy> getOvertimePoliciesByCompany(@PathVariable Long companyId) {
        return overtimePolicyService.getOvertimePoliciesByCompany(companyId);
    }

    @PostMapping
    public OvertimePolicy createOvertimePolicy(@RequestBody @Valid OvertimePolicyRequest request) {
        return overtimePolicyService.createOvertimePolicy(request);
    }

    @PutMapping("/{id}")
    public OvertimePolicy updateOvertimePolicy(@PathVariable Long id, @RequestBody @Valid OvertimePolicyRequest request) {
        return overtimePolicyService.updateOvertimePolicy(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOvertimePolicy(@PathVariable Long id) {
        overtimePolicyService.deleteOvertimePolicy(id);
    }
}
