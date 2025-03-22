package br.upe.booklubapi.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
