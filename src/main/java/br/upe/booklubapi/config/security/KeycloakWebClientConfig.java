package br.upe.booklubapi.config.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class KeycloakWebClientConfig {
    private final KeycloakProperties keycloakProperties;

    @Bean
    @Qualifier("keycloakWebClient")
    public WebClient getWebClient() {
        return WebClient.builder().baseUrl(keycloakProperties.getClientUrl()).build();
    }
}
