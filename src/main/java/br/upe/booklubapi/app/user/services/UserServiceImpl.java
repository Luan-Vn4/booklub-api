package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import reactor.core.publisher.Mono;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.presentation.exceptions.UserHasNoPermissionToException;
import br.upe.booklubapi.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import br.upe.booklubapi.infra.core.KeycloakClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UpdateUserDTOMapper updateUserDTOMapper;
	private final UserDTOMapper userDTOMapper;
	private final UserMediaStorageService userMediaStorageService;
	private final KeycloakClient keycloakClient;
	private final UserUtils userUtils;

	@Override
	public Mono<UserDTO> getByUuid(UUID uuid) {
		return keycloakClient.getUserById(uuid);
	}

	@Override
	public Mono<List<UserDTO>> getByEmail(String email) {
		return keycloakClient.getUserByEmail(email);
	}

	@Override
	@Transactional
	public Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid) {
		userUtils.verifyUserPermission(uuid);

		Mono<UserDTO> userToBeUpdatedDTO = this.getByUuid(uuid);

		User userToBeUpdated = userDTOMapper.toEntity(userToBeUpdatedDTO.block());

		userToBeUpdated = updateUserDTOMapper.partialUpdate(updateUserDTO, userToBeUpdated);

		keycloakClient.updateUserById(userToBeUpdated, uuid);

		String imagePath = userMediaStorageService.saveProfilePicture(updateUserDTO.image(), uuid);

        return keycloakClient.updateProfilePicturePathById(imagePath, userDTOMapper.toDTO(userToBeUpdated), uuid);
	}
}
