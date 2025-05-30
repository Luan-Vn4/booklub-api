package br.upe.booklubapi.domain.users.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import br.upe.booklubapi.domain.users.entities.User;

public interface UserRepositoryCustom {
    Page<User> findByUsernameContaining(String username, Pageable pageble);
}
