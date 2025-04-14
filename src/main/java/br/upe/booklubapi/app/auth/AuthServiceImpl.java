package br.upe.booklubapi.app.auth;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;
import br.upe.booklubapi.infra.core.KeycloakClient;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KeycloakClient keycloakClient;
    private final CreateUserDTOMapper createUserDTOMapper;
    private final UserMediaStorageService userMediaStorageService;

    @Override
    public Mono<Void> register(CreateUserDTO userDTO) { //Essa gambiarra absurda tem que ser feita porque a imagem de perfil tem que ser salva usando o UUID
        if (userDTO.image() == null) {
            return keycloakClient.register(userDTO);
        }
        
        keycloakClient.register(userDTO);
        KeycloakUserDTO savedUser = keycloakClient.getUserByEmail(userDTO.email()).block().get(0); //Trocar isso aqui pra usar o JPA dps
        UUID userUUID = UUID.fromString(savedUser.id());

        String imagePath = userMediaStorageService.saveProfilePicture(userDTO.image(), userUUID);
        
        return keycloakClient.updateProfilePicturePathById(imagePath, userUUID);
    }

    @Override
    public Mono<KeycloakTokenDTO> login(AuthBody authBody) {
        return keycloakClient.login(authBody);
    }
}
,