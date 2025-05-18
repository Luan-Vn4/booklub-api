package br.upe.booklubapi.config.books;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
public class GoogleBooksConfig {

    @Value("${google.books.api.base-url}")
    private String googleBooksBaseUrl;

    @Value("${google.books.api.key}")
    private String googleBooksApiKey;

    @Bean
    public WebClient googleBooksWebClient() {
        return WebClient.builder()
                .baseUrl(googleBooksBaseUrl)
                .defaultUriVariables(Map.of("key", googleBooksApiKey))
                .build();
    }
}
