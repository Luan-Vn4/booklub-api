package br.upe.booklubapi.domain.clubs.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import br.upe.booklubapi.domain.core.repositories.QueryableRepository;
import java.util.UUID;

public interface ClubRepository
    extends CrudRepository<Club, UUID>, QueryableRepository<Club> {}
