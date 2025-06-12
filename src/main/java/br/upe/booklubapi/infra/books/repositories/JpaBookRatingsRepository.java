package br.upe.booklubapi.infra.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookRatings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.books.repositories.BookRatingsRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaBookRatingsRepository
        extends JpaRepository<BookRatings, BookUserId>, BookRatingsRepository {

    @Override
    default <S extends BookRatings> S save(S entity) {
        return saveAndFlush(entity);
    }

    @Override
    default <S extends BookRatings> List<S> saveAll(Iterable<S> entities) {
        return saveAllAndFlush(entities);
    }

    @Override
    Page<BookRatings> findByUserId(UUID userId, Pageable pageable);

    @Override
    @Query("SELECT br FROM BookRatings br WHERE br.id.bookId = :bookId")
    Page<BookRatings> findByBookId(String bookId, Pageable pageable);

}
