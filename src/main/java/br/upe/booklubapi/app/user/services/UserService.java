package br.upe.booklubapi.app.user.services;

import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;

public interface UserService {
    CreateUserDTO create(CreateUserDTO createUserDTO);
    
    UserDTO getByUuid(UUID uuid);
    UserDTO getByEmail(String email);

    UserDTO update(CreateUserDTO dto, UUID uuid);

    void delete(UUID uuid);
}
