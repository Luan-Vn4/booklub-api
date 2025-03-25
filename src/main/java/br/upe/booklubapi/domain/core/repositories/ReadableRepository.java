package br.upe.booklubapi.domain.core.repositories;

import br.upe.booklubapi.domain.clubs.entities.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ReadableRepository<T, ID> {

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Page<Club> findAll(Pageable pageable);

    Page<Club> findAllByIdIn(Collection<UUID> uuids, Pageable pageable);

    long count();

}
