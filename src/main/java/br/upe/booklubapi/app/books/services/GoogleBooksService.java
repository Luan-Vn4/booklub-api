package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookItemQuery;
import br.upe.booklubapi.app.books.dtos.BookSearchResponse;

public interface GoogleBooksService {

    BookSearchResponse searchBooks(BookItemQuery query);
}
