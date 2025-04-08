package br.upe.booklubapi.app.auth;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;

public interface AuthService {
    CreateUserDTO register(CreateUserDTO createUserDTO);
    KeycloakTokenDTO login(AuthBody user);
}
