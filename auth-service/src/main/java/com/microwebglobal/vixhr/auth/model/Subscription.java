package com.microwebglobal.vixhr.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageType;

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
}
