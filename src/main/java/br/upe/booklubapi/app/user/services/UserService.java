package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import reactor.core.publisher.Mono;

public interface UserService {
    UserDTO getByUuid(UUID uuid);

    UserDTO getByEmail(String email);

    Mono<Void> updateById(UpdateUserDTO updateUserDTO, UUID uuid);

    PagedModel<UserDTO> findByUsernameContaining(String username, Pageable page);
}
