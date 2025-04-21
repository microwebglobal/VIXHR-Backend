package com.microwebglobal.vixhr.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microwebglobal.vixhr.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feature extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 100)
    private String name;

    @NotNull
    @Column(unique = true)
    @Length(min = 2, max = 50)
    private String code;

    @Length(min = 2, max = 500)
    private String description;

    @NotNull
    @Length(min = 2, max = 50)
    private String module;
}
