package br.upe.booklubapi.app.auth;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.AuthResponseDTO;
import br.upe.booklubapi.app.auth.dto.TokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.infra.core.KeycloakClient;
import br.upe.booklubapi.utils.UserUtils;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakClient keycloakClient;
    private final UserMediaStorageService userMediaStorageService;
    private final UserUtils userUtils;

    @Override
    public Mono<Void> register(CreateUserDTO userDTO) { //Essa gambiarra absurda tem que ser feita porque a imagem de perfil tem que ser salva usando o UUID
        if (userDTO.image() == null) {
            return keycloakClient.register(userDTO);
        }
        
        keycloakClient.register(userDTO).block();
        UserDTO savedUser = keycloakClient.getUserByEmail(userDTO.email()).block().get(0); //Trocar isso aqui pra usar o JPA dps
        UUID userUUID = UUID.fromString(savedUser.id());

        String imagePath = userMediaStorageService.saveProfilePicture(userDTO.image(), userUUID);

        return keycloakClient.updateProfilePicturePathById(imagePath, savedUser, userUUID);
    }

    @Override
    public AuthResponseDTO login(AuthBody authBody) {
        TokenDTO tokenDTO = keycloakClient.login(authBody);

        UserDTO userDTO = keycloakClient.getUserById(userUtils.getUserIdFromUserToken(tokenDTO.accessToken())).block();

        return new AuthResponseDTO(userDTO, tokenDTO);
    }

    @Override
	public Mono<Void> deleteById(UUID uuid) {
		userUtils.verifyUserPermission(uuid);
		return keycloakClient.deleteUserById(uuid);
	}
}