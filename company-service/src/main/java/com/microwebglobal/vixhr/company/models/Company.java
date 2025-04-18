package com.microwebglobal.vixhr.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "companies")
public class Company extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Positive
    private Long tenantId;

    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @Length(min = 2, max = 50)
    private String registrationNumber;

    @Length(min = 2, max = 50)
    private String taxId;

    @Length(min = 2, max = 100)
    private String address;

    @Length(min = 10, max = 10)
    private String phone;

    @Email
    private String email;

    @Length(min = 4, max = 50)
    private String website;

    private String city;

    private String state;

    private String country;

    private String postalCode;
}
