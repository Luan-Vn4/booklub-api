package br.upe.booklubapi.infra.users;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;

@Repository
public interface JpaUserRepository
        extends JpaRepository<User, UUID>, UserRepository {
        
}
