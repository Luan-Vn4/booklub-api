package br.upe.booklubapi.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.upe.booklubapi.presentation.exceptions.User.UserNotFoundException;
import br.upe.booklubapi.presentation.exceptions.User.UserNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(UserNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotValidException(UserNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}