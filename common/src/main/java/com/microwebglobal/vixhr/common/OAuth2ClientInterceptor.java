package com.microwebglobal.vixhr.common;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OAuth2ClientInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AccessToken accessToken;
    private static final String REGISTRATION_ID = "test-client";
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public @NonNull ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            @NonNull byte[] body,
            @NonNull ClientHttpRequestExecution execution
    ) throws IOException {
        OAuth2AccessToken token = getAccessToken();
        if (token != null) {
            request.getHeaders().setBearerAuth(token.getTokenValue());
        }
        return execution.execute(request, body);
    }

    private synchronized OAuth2AccessToken getAccessToken() {
        if (isTokenValid(accessToken)) {
            return accessToken;
        }

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(REGISTRATION_ID)
                .principal("system")
                .build();

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient != null) {
            accessToken = authorizedClient.getAccessToken();
        }

        return accessToken;
    }

    private static boolean isTokenValid(@Nullable OAuth2AccessToken token) {
        return token != null &&
                token.getExpiresAt() != null &&
                token.getExpiresAt()
                        .isAfter(Instant.now().minusSeconds(60)); // Add small buffer
    }
}

