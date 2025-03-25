package br.upe.booklubapi.domain.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.Optional;

public interface ReadableRepository<T, ID> {

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Page<T> findAll(Pageable pageable);

    Page<T> findAllByIdIn(Collection<ID> uuids, Pageable pageable);

    long count();

}
