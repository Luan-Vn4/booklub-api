package br.upe.booklubapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
