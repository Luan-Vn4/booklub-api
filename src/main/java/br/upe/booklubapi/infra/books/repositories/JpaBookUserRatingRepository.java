package br.upe.booklubapi.infra.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookRatings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.books.repositories.BookRatingsRepository;

import java.util.List;

@Repository
public interface JpaBookUserRatingRepository
        extends JpaRepository<BookRatings, BookUserId>, BookRatingsRepository {

    @Override
    default <S extends BookRatings> S save(S entity) {
        return saveAndFlush(entity);
    }

    @Override
    default <S extends BookRatings> List<S> saveAll(Iterable<S> entities) {
        return saveAllAndFlush(entities);
    }

}
