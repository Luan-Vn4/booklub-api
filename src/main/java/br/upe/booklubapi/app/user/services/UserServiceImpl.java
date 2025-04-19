package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import reactor.core.publisher.Mono;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.auth.exceptions.PermissionDeniedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import br.upe.booklubapi.infra.core.KeycloakClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UpdateUserDTOMapper updateUserDTOMapper;
	private final UserDTOMapper userDTOMapper;
	private final KeycloakClient keycloakClient;

	JwtDecoder jwtDecoder;

	@Override
	public Mono<UserDTO> getByUuid(UUID uuid) {
		return keycloakClient.getUserById(uuid);
	}

	@Override
	public Mono<List<UserDTO>> getByEmail(String email) {
		return keycloakClient.getUserByEmail(email);
	}

	@Override
	public Mono<Void> deleteById(UUID uuid) {
		userHasPermission(uuid);
		return keycloakClient.deleteUserById(uuid);
	}

	@Override
	@Transactional
	public Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid) {
		userHasPermission(uuid);

		Mono<UserDTO> userToBeUpdatedDTO = this.getByUuid(uuid);

		User userToBeUpdated = userDTOMapper.toEntity(userToBeUpdatedDTO.block());

		updateUserDTO = new UpdateUserDTO(
				userToBeUpdated.getId(),
				updateUserDTO.username(),
				updateUserDTO.email(),
				updateUserDTO.firstName(),
				updateUserDTO.lastName(),
				updateUserDTO.image());

		userToBeUpdated = updateUserDTOMapper.partialUpdate(updateUserDTO, userToBeUpdated);

		return keycloakClient.updateUserById(userToBeUpdated, uuid);
	}

	private String getUserToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();

		return request.getHeader("Authorization").substring(7);
	}

	private void userHasPermission(UUID idOfUserObjectReceivingChanges) {
		String userToken = getUserToken();
		String requestIssuerId = jwtDecoder.decode(userToken).getSubject();

		if (!requestIssuerId.equals(idOfUserObjectReceivingChanges.toString())) {
			throw new PermissionDeniedException("alterar usuário de id" + idOfUserObjectReceivingChanges);
		}
	}

}
