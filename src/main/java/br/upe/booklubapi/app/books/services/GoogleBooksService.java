package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookItem;
import br.upe.booklubapi.app.books.dtos.BookItemQuery;

import java.util.List;

public interface GoogleBooksService {

    List<BookItem> searchBooks(BookItemQuery query);
}
