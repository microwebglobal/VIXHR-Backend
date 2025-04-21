package com.microwebglobal.vixhr.auth.service;

import com.microwebglobal.vixhr.auth.dto.SubscriptionRequest;
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

    public Subscription createSubscription(SubscriptionRequest request) {
        var tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found for ID: " + request.getTenantId()));

        var packageType = packageRepository.findById(request.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + request.getPackageId()));

        var subscription = request.toSubscription();
        subscription.setTenant(tenant);
        subscription.setPackageType(packageType);
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, SubscriptionRequest request) {
        subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found for ID: " + id));

        var tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found for ID: " + request.getTenantId()));

        var packageType = packageRepository.findById(request.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found for ID: " + request.getPackageId()));

        var subscription = request.toSubscription();
        subscription.setId(id);
        subscription.setTenant(tenant);
        subscription.setPackageType(packageType);
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id) {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found for ID: " + id));

        subscriptionRepository.delete(subscription);
    }
}
