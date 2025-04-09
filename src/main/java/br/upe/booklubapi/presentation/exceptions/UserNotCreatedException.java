package br.upe.booklubapi.presentation.exceptions;

public class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException() {
        super("Não foi possível cadastrar o usuário");
    }
}