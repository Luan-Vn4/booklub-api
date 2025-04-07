package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;

public interface UserService {
    KeycloakUserDTO getByUuid(UUID uuid);
    List<KeycloakUserDTO> getByEmail(String email);
    void deleteById(UUID uuid);
}
