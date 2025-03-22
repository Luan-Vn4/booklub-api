package br.upe.booklubapi.app.user.services;

import java.util.Optional;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;

public interface UserService {
    CreateUserDTO create(CreateUserDTO createUserDTO);
    
    Optional<CreateUserDTO> getByUuid(UUID uuid);
    Optional<CreateUserDTO> getByEmail(String email);

    CreateUserDTO update(CreateUserDTO dto, UUID uuid);

    void delete(UUID uuid);
}
