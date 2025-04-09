package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<KeycloakUserDTO> getByUuid(UUID uuid);

    Mono<List<KeycloakUserDTO>> getByEmail(String email);

    Mono<Void> deleteById(UUID uuid);

    Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid);
}
