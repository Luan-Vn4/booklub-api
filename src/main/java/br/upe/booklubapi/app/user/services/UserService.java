package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDTO> getByUuid(UUID uuid);

    Mono<List<UserDTO>> getByEmail(String email);

    Mono<Void> deleteById(UUID uuid);

    Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid);
}
