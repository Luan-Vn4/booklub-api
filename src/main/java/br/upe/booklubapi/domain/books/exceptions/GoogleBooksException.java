package br.upe.booklubapi.domain.books.exceptions;

public class GoogleBooksException extends RuntimeException {
    public GoogleBooksException(String message, Throwable cause) {
        super(message, cause);
    }
}
