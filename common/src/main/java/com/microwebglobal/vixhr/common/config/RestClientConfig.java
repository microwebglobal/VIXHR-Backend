package com.microwebglobal.vixhr.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private String getCurrentAccessToken() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken token) {
            return token.getToken().getTokenValue();
        }
        return null;
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestInitializer(request -> {
                    String token = getCurrentAccessToken();
                    if (token != null) {
                        request.getHeaders().setBearerAuth(token);
                    }
                })
                .build();
    }
}
