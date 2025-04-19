package br.upe.booklubapi.domain.auth.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Login ou senha inválidos");
    }

}
