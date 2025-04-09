package com.microwebglobal.vixhr.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderForwardingFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Extract the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // Forwarding the token
        if (authHeader != null) {
            exchange = exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .build())
                    .build();
        }

        return chain.filter(exchange);
    }
}

