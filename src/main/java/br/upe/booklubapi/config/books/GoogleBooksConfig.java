package br.upe.booklubapi.config.books;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GoogleBooksConfig {

    @Value("${google.books.api.base-url}")
    private String googleBooksBaseUrl;

    @Bean
    public WebClient googleBooksWebClient() {
        return WebClient.builder()
                .baseUrl(googleBooksBaseUrl)
                .build();
    }
}
