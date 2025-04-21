package com.microwebglobal.vixhr.auth.service;

import com.microwebglobal.vixhr.auth.model.Tenant;
import com.microwebglobal.vixhr.auth.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    public Iterable<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id).orElse(null);
    }

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, Tenant tenant) {
        var existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found for ID: " + id));

        existingTenant.setName(tenant.getName());
        existingTenant.setDomain(tenant.getDomain());
        existingTenant.setSubdomain(tenant.getSubdomain());
        existingTenant.setLogoUrl(tenant.getLogoUrl());
        existingTenant.setPrimaryColor(tenant.getPrimaryColor());
        existingTenant.setSecondaryColor(tenant.getSecondaryColor());
        existingTenant.setActive(tenant.isActive());

        return tenantRepository.save(existingTenant);
    }

    public void deleteTenant(Long id) {
        var tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found for ID: " + id));

        tenantRepository.delete(tenant);
    }
}
