package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookItemQuery;
import br.upe.booklubapi.app.books.dtos.BookSearchResponse;
import br.upe.booklubapi.infra.core.gateways.GoogleBooks.GoogleBooksGateway;
import org.springframework.stereotype.Service;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {


    private final GoogleBooksGateway googleGateway;

    public GoogleBooksServiceImpl(GoogleBooksGateway googleGateway) {
        this.googleGateway = googleGateway;
    }

    @Override
    public BookSearchResponse searchBooks(BookItemQuery query) {
        return googleGateway.searchBooks(query.toString());
    }
}
