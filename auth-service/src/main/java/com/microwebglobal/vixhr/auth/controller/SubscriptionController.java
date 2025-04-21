package com.microwebglobal.vixhr.auth.controller;

import com.microwebglobal.vixhr.auth.service.SubscriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@SecurityRequirement(name = "oauth")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
}
