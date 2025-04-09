package br.upe.booklubapi.presentation.exceptions;

public class UserHasNoPermissionToException extends RuntimeException {
    private String message;
    
    public UserHasNoPermissionToException(String message) {
        super("O usuário não têm permissão para " + message);
    }
}
