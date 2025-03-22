package br.upe.booklubapi.presentation.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.upe.booklubapi.presentation.exceptions.ErrorMessages;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordNotValidException extends RuntimeException {
    public PasswordNotValidException() {
        super(ErrorMessages.PASSWORD_NOT_VALID.getMessage());
    }
}
