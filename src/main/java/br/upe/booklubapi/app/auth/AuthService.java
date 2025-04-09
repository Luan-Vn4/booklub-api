package br.upe.booklubapi.app.auth;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Void> register(CreateUserDTO createUserDTO);
    Mono<KeycloakTokenDTO> login(AuthBody authBody);
}
