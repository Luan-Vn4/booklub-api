package br.upe.booklubapi.presentation.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID uuid) {
        super("Não foi possível encontrar um usuário com o UUID: " + uuid);
    }

    public UserNotFoundException(String email) {
        super("Não foi possível encontrar um usuário com o e-mail: " + email);
    }
}
