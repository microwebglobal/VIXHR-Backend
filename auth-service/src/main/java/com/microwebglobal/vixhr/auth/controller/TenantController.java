package com.microwebglobal.vixhr.auth.controller;

import com.microwebglobal.vixhr.auth.model.Tenant;
import com.microwebglobal.vixhr.auth.service.TenantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tenants")
@SecurityRequirement(name = "oauth")
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public Iterable<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }

    @PostMapping
    public Tenant createTenant(@RequestBody @Valid Tenant tenant) {
        return tenantService.createTenant(tenant);
    }

    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody @Valid Tenant tenant) {
        return tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}
