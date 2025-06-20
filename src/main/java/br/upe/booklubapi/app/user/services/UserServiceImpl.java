package br.upe.booklubapi.app.user.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.exceptions.UserNotFoundException;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.domain.users.repository.UserRepositoryCustom;
import br.upe.booklubapi.infra.core.gateways.Keycloak.KeycloakRestApiGateway;
import br.upe.booklubapi.utils.UserUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UpdateUserDTOMapper updateUserDTOMapper;
	private final UserDTOMapper userDTOMapper;
	private final KeycloakRestApiGateway keycloakClient;
	private final UserUtils userUtils;
	private final UserRepository userRepository;
	private final UserRepositoryCustom userRepositoryCustom;


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
	public UserDTO updateById(UpdateUserDTO updateUserDTO, UUID uuid) {
		userUtils.verifyUserPermission(uuid);

		User userToBeUpdated = userRepository.findById(uuid).get();

		userToBeUpdated = updateUserDTOMapper.partialUpdate(updateUserDTO, userToBeUpdated);

		keycloakClient.updateUserById(userToBeUpdated, uuid);

		return userDTOMapper.toDTO(userToBeUpdated);
	}

	@Override
	public PagedModel<UserDTO> findByUsernameContaining(String username, Pageable page) {

		return new PagedModel<>(
            userRepositoryCustom.findByUsernameContaining(username, page)
                .map(userDTOMapper::toDTO)
        );
	}
}
