package br.upe.booklubapi.domain.books.repositories;

import org.springframework.stereotype.Repository;

import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;

@Repository
public interface BookUserRatingRepository extends CrudRepository<BookRatings, BookUserId>  {
}
