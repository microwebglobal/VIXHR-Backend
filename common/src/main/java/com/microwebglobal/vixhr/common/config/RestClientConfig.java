package com.microwebglobal.vixhr.common.config;

import com.microwebglobal.vixhr.common.OAuth2ClientInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                ClientRegistration.withRegistrationId("test-client")
                        .clientId("test-client")
                        .clientSecret("secret")
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .tokenUri("http://localhost:8081/oauth2/token")
                        .build()
        );
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository repo
    ) {
        return new InMemoryOAuth2AuthorizedClientService(repo);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository repo,
            OAuth2AuthorizedClientService service
    ) {
        var provider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials().build();

        var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(repo, service);
        manager.setAuthorizedClientProvider(provider);
        return manager;
    }

    @Bean
    public OAuth2ClientInterceptor oAuth2ClientInterceptor(OAuth2AuthorizedClientManager manager) {
        return new OAuth2ClientInterceptor(manager);
    }

    @Bean
    @LoadBalanced
    public RestClient restClient(OAuth2ClientInterceptor interceptor, RestClient.Builder builder) {
        return builder
                .requestInterceptor(interceptor)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
                    String body = response.getBody().toString();
                    throw new RuntimeException("Server error: " + body);
                })
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                    String body = response.getBody().toString();
                    throw new RuntimeException("Client error: " + body);
                })
                .build();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
