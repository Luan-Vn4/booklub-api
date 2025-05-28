package br.upe.booklubapi.app.user.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import reactor.core.publisher.Mono;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.exceptions.UserNotFoundException;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.infra.core.gateways.Keycloak.KeycloakRestApiGateway;
import br.upe.booklubapi.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UpdateUserDTOMapper updateUserDTOMapper;
	private final UserDTOMapper userDTOMapper;
	private final UserMediaStorageService userMediaStorageService;
	private final KeycloakRestApiGateway keycloakClient;
	private final UserUtils userUtils;
	private final UserRepository userRepository;

	@Override
	public UserDTO getByUuid(UUID uuid) {
		User user = userRepository.findById(uuid).orElseThrow(
            () -> new UserNotFoundException(uuid)
        );

		return userDTOMapper.toDTO(user);
	}

	@Override
	public UserDTO getByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(
            () -> new UserNotFoundException(email)
        );

		return userDTOMapper.toDTO(user);
	}

	@Override
	@Transactional
	public Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid) {
		userUtils.verifyUserPermission(uuid);

		User userToBeUpdated = userRepository.findById(uuid).get();

		userToBeUpdated = updateUserDTOMapper.partialUpdate(updateUserDTO, userToBeUpdated);

		keycloakClient.updateUserById(userToBeUpdated, uuid);

		String imagePath = userMediaStorageService.saveProfilePicture(updateUserDTO.image(), uuid);

		userToBeUpdated.setImage(imagePath);

		return keycloakClient.updateProfilePicturePathById(imagePath, userDTOMapper.toDTO(userToBeUpdated), uuid);
	}
}
