package br.upe.booklubapi.app.auth;

import java.util.UUID;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Void> register(CreateUserDTO createUserDTO);
    Mono<KeycloakTokenDTO> login(AuthBody authBody);
    Mono<Void> deleteById(UUID uuid);
}
