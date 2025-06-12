package br.upe.booklubapi.domain.books.exceptions;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class BookRatingsNotFoundException extends HttpResponseException {

    public BookRatingsNotFoundException(BookUserId id) {
        super(
            HttpStatus.NOT_FOUND,
            "No Rating Found",
            "User of id %s didn't evaluate the book of id %s"
                .formatted(id.getUserId(), id.getBookId())
        );
    }

}
