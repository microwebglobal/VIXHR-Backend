package com.microwebglobal.vixhr.auth.dto;

import com.microwebglobal.vixhr.auth.model.Subscription;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {

    @NotNull
    private Long tenantId;

    @NotNull
    private Long packageId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Length(min = 2, max = 20)
    private String billingCycle;

    @NotNull
    @DecimalMin(value = "0.0")
    private double amount;

    @NotNull
    @Length(min = 2, max = 20)
    private String status;

    public Subscription toSubscription() {
        Subscription subscription = new Subscription();
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        subscription.setBillingCycle(billingCycle);
        subscription.setAmount(amount);
        subscription.setStatus(status);
        return subscription;
    }
}
