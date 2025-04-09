package br.upe.booklubapi.app.auth;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.infra.core.KeycloakClient;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakClient keycloakClient;

    @Override
    public Mono<Void> register(CreateUserDTO userDTO) {
        return keycloakClient.register(userDTO);
    }

    @Override
    public Mono<KeycloakTokenDTO> login(AuthBody authBody) {
        return keycloakClient.login(authBody);
    }
}
