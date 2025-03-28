package br.upe.booklubapi.domain.core.repositories;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface QueryableRepository<T> {

    Optional<T> findOne(Predicate predicate);

    Page<T> findAll(Predicate predicate, Pageable pageable);

    long count(Predicate predicate);

    boolean exists(Predicate predicate);

}
