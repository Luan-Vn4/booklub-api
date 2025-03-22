package br.upe.booklubapi.presentation.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.upe.booklubapi.presentation.exceptions.ErrorMessages;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super(ErrorMessages.USER_NOT_FOUND.getMessage());
    }
}