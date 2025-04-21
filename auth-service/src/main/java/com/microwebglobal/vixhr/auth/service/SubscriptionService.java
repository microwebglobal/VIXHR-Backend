package com.microwebglobal.vixhr.auth.service;

import com.microwebglobal.vixhr.auth.model.Subscription;
import com.microwebglobal.vixhr.auth.repository.PackageRepository;
import com.microwebglobal.vixhr.auth.repository.SubscriptionRepository;
import com.microwebglobal.vixhr.auth.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final TenantRepository tenantRepository;
    private final PackageRepository packageRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Iterable<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found for ID: " + id));
    }

    public Subscription createSubscription(Subscription subscription) {
        var tenant = tenantRepository.findById(subscription.getTenant().getId())
                .orElseThrow(() -> new RuntimeException("Tenant not found for ID: " + subscription.getTenant().getId()));

        var packageType = packageRepository.findById(subscription.getPackageType().getId())
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + subscription.getPackageType().getId()));

        subscription.setTenant(tenant);
        subscription.setPackageType(packageType);
        subscription.setStatus("Active");

        return subscriptionRepository.save(subscription);
    }
}
