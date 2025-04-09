package com.microwebglobal.vixhr.common.config;

import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.stream.Collectors;

public class JwtAuthorizationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    @Nonnull
    public <U> Converter<Jwt, U> andThen(@Nonnull Converter<? super Collection<GrantedAuthority>, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        var roles = source.getClaimAsStringList("roles");

        if (roles == null) {
            return null;
        }

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
