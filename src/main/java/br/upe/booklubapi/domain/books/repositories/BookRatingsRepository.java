package br.upe.booklubapi.domain.books.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;

import java.util.UUID;

@Repository
public interface BookRatingsRepository
    extends CrudRepository<BookRatings, BookUserId>  {

    Page<BookRatings> findByUserId(UUID userId, Pageable pageable);

    Page<BookRatings> findByBookId(String bookId, Pageable pageable);

}
