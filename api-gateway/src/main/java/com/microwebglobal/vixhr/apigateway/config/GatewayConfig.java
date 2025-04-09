package com.microwebglobal.vixhr.apigateway.config;

import com.microwebglobal.vixhr.apigateway.filters.AuthorizationHeaderForwardingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.filter.GatewayFilter;

@Configuration
public class GatewayConfig {

    @Bean
    public GatewayFilter authHeaderForwardingFilter() {
        return new AuthorizationHeaderForwardingFilter();
    }
}
