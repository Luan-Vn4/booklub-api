package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;

public interface UserService {
    KeycloakUserDTO getByUuid(UUID uuid);
    List<KeycloakUserDTO> getByEmail(String email);
    void deleteById(UUID uuid);
    KeycloakUserDTO updateById(UpdateUserDTO updateUserDTO, UUID uuid);
}
