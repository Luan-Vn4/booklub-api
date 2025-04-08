package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.KeycloakUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import br.upe.booklubapi.config.KeycloakProperties;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;
import br.upe.booklubapi.presentation.exceptions.UserHasNoPermissionToException;
import br.upe.booklubapi.utils.KeycloakUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final KeycloakProperties keycloakProperties;
	private final KeycloakUtils keycloakUtils;
	private final UpdateUserDTOMapper updateUserDTOMapper;
	private final KeycloakUserDTOMapper keycloakUserDTOMapper;

	JwtDecoder jwtDecoder;

	@Override
	public KeycloakUserDTO getByUuid(UUID uuid) {
		String adminToken = keycloakUtils.getAdminToken();

		KeycloakUserDTO userDTO = WebClient.create()
				.get()
				.uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
						+ "/users/" + uuid)
				.header("Authorization", "Bearer " + adminToken)
				.retrieve()
				.bodyToMono(KeycloakUserDTO.class)
				.block();

		return userDTO;
	}

	@Override
	public List<KeycloakUserDTO> getByEmail(String email) {
		String adminToken = keycloakUtils.getAdminToken();

		List<KeycloakUserDTO> users = WebClient.create()
				.get()
				.uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
						+ "/users?email=" + email)
				.header("Authorization", "Bearer " + adminToken)
				.retrieve()
				.bodyToFlux(KeycloakUserDTO.class)
				.collectList()
				.block();

		return users;
	}

	@Override
	public void deleteById(UUID uuid) {
		userHasPermission(uuid);

		String adminToken = keycloakUtils.getAdminToken();
		WebClient.create()
				.delete()
				.uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
						+ "/users/" + uuid)
				.header("Authorization", "Bearer " + adminToken)
				.retrieve()
				.toBodilessEntity()
				.block();
	}

	@Override
	@Transactional
	public KeycloakUserDTO updateById(UpdateUserDTO updateUserDTO, UUID uuid) {
		userHasPermission(uuid);
		
		KeycloakUserDTO userToBeUpdatedDTO = this.getByUuid(uuid);

		KeycloakUser userToBeUpdated = keycloakUserDTOMapper.toEntity(userToBeUpdatedDTO);

		String adminToken = keycloakUtils.getAdminToken();

		userToBeUpdated = updateUserDTOMapper.partialUpdate(updateUserDTO, userToBeUpdated);

		String user = "{"
				+ "\"username\": \"" + userToBeUpdated.getUsername() + "\","
				+ "\"email\": \"" + userToBeUpdated.getEmail() + "\","
				+ "\"firstName\": \"" + userToBeUpdated.getFirstName() + "\","
				+ "\"lastName\": \"" + userToBeUpdated.getLastName() + "\","
				+ "\"enabled\": true,"
				+ "\"attributes\": {"
				+ "\"imageUrl\": [\"" + userToBeUpdated.getAttributes().get("imageUrl").get(0) + "\"]"
				+ "}"
				+ "}";

		WebClient.create()
				.put()
				.uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
						+ "/users/" + uuid)
				.header("Authorization", "Bearer " + adminToken)
				.header("Content-Type", "application/json")
				.bodyValue(user)
				.retrieve()
				.toBodilessEntity()
				.block();

		return keycloakUserDTOMapper.toDto(userToBeUpdated);
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
			throw new UserHasNoPermissionToException("alterar usuário de id" + idOfUserObjectReceivingChanges);
		}
	}

	

}
