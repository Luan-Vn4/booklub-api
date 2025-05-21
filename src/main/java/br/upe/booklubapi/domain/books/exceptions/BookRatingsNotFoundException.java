package br.upe.booklubapi.domain.books.exceptions;

import br.upe.booklubapi.domain.books.entities.BookUserId;

public class BookRatingsNotFoundException extends RuntimeException {
    public BookRatingsNotFoundException(BookUserId id) {
        super(String.format("User of id %s didn't evaluate the book of id %s", id.getUserId(), id.getBookId()));
    }
}
