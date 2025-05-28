package br.upe.booklubapi.app.auth;

import java.util.UUID;

import org.springframework.stereotype.Service;
import br.upe.booklubapi.app.user.services.UserService;
import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.AuthResponseDTO;
import br.upe.booklubapi.app.auth.dto.TokenDTO;
import br.upe.booklubapi.app.auth.dto.RecoverUserPasswordDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.infra.core.gateways.Keycloak.KeycloakRestApiGateway;
import br.upe.booklubapi.utils.UserUtils;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakRestApiGateway keycloakGateway;
    private final UserMediaStorageService userMediaStorageService;
    private final UserUtils userUtils;
    private final UserService userService;

    @Override
    public Mono<Void> register(CreateUserDTO userDTO) { //Essa gambiarra absurda tem que ser feita porque a imagem de perfil tem que ser salva usando o UUID
        if (userDTO.image().isEmpty()) {
            return keycloakGateway.register(userDTO);
        }
        
        keycloakGateway.register(userDTO).block();
        UserDTO savedUser = userService.getByEmail(userDTO.email());
        UUID userUUID = UUID.fromString(savedUser.id());

        String imagePath = userMediaStorageService.saveProfilePicture(userDTO.image(), userUUID);

        return keycloakGateway.updateProfilePicturePathById(imagePath, savedUser, userUUID);
    }

    @Override
    public AuthResponseDTO login(AuthBody authBody) {
        TokenDTO tokenDTO = keycloakGateway.login(authBody);

        UserDTO userDTO = userService.getByUuid(userUtils.getUserIdFromUserToken(tokenDTO.accessToken()));

        return new AuthResponseDTO(userDTO, tokenDTO);
    }

    @Override
	public Mono<Void> updateUserPassword(RecoverUserPasswordDTO dto) {
        String userId = userService.getByEmail(dto.email()).id();

		return keycloakGateway.recoverUserPassword(UUID.fromString(userId));
	}

    @Override
	public Mono<Void> deleteById(UUID uuid) {
		userUtils.verifyUserPermission(uuid);
		return keycloakGateway.deleteUserById(uuid);
	}
}