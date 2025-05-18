package br.upe.booklubapi.infra.core.gateways.GoogleBooks;

import br.upe.booklubapi.app.books.dtos.BookSearchResponse;
import br.upe.booklubapi.domain.books.exceptions.GoogleBooksException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GoogleBooksGateway {

    private final WebClient webClient;
    private final String apiKey;


    public GoogleBooksGateway(@Qualifier("googleBooksWebClient") WebClient webClient,
                              @Value("${google.books.api.key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }


    public BookSearchResponse searchBooks(String query) {
        log.info("Searching books on Google Books API with query: {}", query);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", query)
                        .queryParam("key", apiKey)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BookSearchResponse.class)
                .doOnSuccess(response -> log.info("Successfully retrieved books"))
                .doOnError(error -> log.error("Error retrieving books", error))
                .onErrorMap(ex -> new GoogleBooksException("Failed to fetch books from Google Books API", ex))
                .block();
    }
}
