package com.microwebglobal.vixhr.company.config;

import com.microwebglobal.vixhr.common.config.JwtAuthorizationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/company/v3/api-docs/**",
                                "/company/swagger-ui/**",
                                "/company/swagger-ui.html")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(
                                jwt -> jwt.jwtAuthenticationConverter(authorizationConverter())
                        )
                );

        return http.build();
    }

    @Bean
    JwtAuthenticationConverter authorizationConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new JwtAuthorizationConverter()
        );
        return converter;
    }
}
