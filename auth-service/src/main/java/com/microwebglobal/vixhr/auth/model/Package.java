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

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "packages")
public class Package extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @Length(min = 2, max = 500)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0")
    private double basePrice;

    @NotNull
    private Integer maxEmployees;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "packageType", fetch = FetchType.EAGER)
    private Set<PackageFeature> packageFeatures;
}
