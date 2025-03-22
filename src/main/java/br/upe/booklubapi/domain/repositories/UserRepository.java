package br.upe.booklubapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
