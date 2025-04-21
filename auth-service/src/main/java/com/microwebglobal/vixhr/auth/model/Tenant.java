package com.microwebglobal.vixhr.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenants")
public class Tenant extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 100)
    private String name;

    @Length(min = 2, max = 100)
    private String domain;

    @NotNull
    @Column(unique = true)
    @Length(min = 2, max = 100)
    private String subdomain;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String logoUrl;

    @Length(min = 2, max = 20)
    private String primaryColor;

    @Length(min = 2, max = 20)
    private String secondaryColor;

    private boolean active = true;
}
