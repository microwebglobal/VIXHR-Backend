package com.microwebglobal.vixhr.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "package_id", unique = true)
    private Package packageType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "feature_id", unique = true)
    private Feature feature;

    private boolean enabled = true;
}
