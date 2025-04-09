package br.upe.booklubapi.presentation.exceptions;

public class AuthBodyInvalidException extends RuntimeException {
    public AuthBodyInvalidException() {
        super("Login ou senha inválidos");
    }
}
