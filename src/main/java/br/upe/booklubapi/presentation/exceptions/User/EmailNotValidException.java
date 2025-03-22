package br.upe.booklubapi.presentation.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.upe.booklubapi.presentation.exceptions.ErrorMessages;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException() {
        super(ErrorMessages.EMAIL_NOT_VALID.getMessage());
    }
}
