package com.microwebglobal.vixhr.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "companies")
public class company extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
}
