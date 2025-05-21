package br.upe.booklubapi.domain.books.exceptions;

import br.upe.booklubapi.domain.books.entities.BookUserId;

public class ProgressNotFoundException extends RuntimeException {
    public ProgressNotFoundException(BookUserId id) {
        super(String.format("Progress with id %s not found", id));
    }
}
