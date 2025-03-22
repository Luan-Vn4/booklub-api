package br.upe.booklubapi.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.upe.booklubapi.presentation.exceptions.User.EmailNotValidException;
import br.upe.booklubapi.presentation.exceptions.User.PasswordNotValidException;
import br.upe.booklubapi.presentation.exceptions.User.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(EmailNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleEmailNotValidException(EmailNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePasswordNotValidException(PasswordNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}