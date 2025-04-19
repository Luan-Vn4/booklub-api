package br.upe.booklubapi.domain.auth.exceptions;

public class RegistrationException extends RuntimeException {

    public RegistrationException() {
        super("Não foi possível cadastrar o usuário");
    }

}