package br.upe.booklubapi.domain.books.repositories;

import java.util.UUID;

import br.upe.booklubapi.domain.books.entities.BookUserRating;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;

public interface BookUserRatingRepository extends CrudRepository<BookUserRating, UUID>  {
    
}
