package com.microwebglobal.vixhr.auth.controller;

import com.microwebglobal.vixhr.auth.dto.SubscriptionRequest;
import com.microwebglobal.vixhr.auth.model.Subscription;
import com.microwebglobal.vixhr.auth.service.SubscriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@SecurityRequirement(name = "oauth")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public Iterable<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public Subscription getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody @Valid SubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }

    @PutMapping("/{id}")
    public Subscription updateSubscription(@PathVariable Long id, @RequestBody @Valid SubscriptionRequest request) {
        return subscriptionService.updateSubscription(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }
}
