package br.upe.booklubapi.domain.books.exceptions;

import br.upe.booklubapi.domain.books.entities.BookUserId;

public class BookUserNotFoundException extends RuntimeException {
    public BookUserNotFoundException(BookUserId id) {
        super(String.format("User of id %s doesn't hold a relation to book of id %s", id.getUserId(), id.getBookId()));
    }
}
