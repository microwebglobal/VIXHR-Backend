package com.microwebglobal.vixhr.auth.repository;

import com.microwebglobal.vixhr.auth.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}