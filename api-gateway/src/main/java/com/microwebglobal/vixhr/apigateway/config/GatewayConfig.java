package com.microwebglobal.vixhr.apigateway.config;

import com.microwebglobal.vixhr.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public GlobalFilter jwtAuthFilter() {
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String path = exchange.getRequest().getURI().getPath();

                // Skip authentication for public endpoints
                if (path.equals("/api/users/register") || path.equals("/api/users/login") ||
                        path.startsWith("/actuator")) {
                    return chain.filter(exchange);
                }

                // Apply JWT filter for all other paths
                return jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config())
                        .filter(exchange, chain);
            }
        };
    }
}