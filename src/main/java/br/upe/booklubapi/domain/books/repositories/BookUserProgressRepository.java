package br.upe.booklubapi.domain.books.repositories;

import java.util.UUID;

import br.upe.booklubapi.domain.books.entities.BookUserProgress;
import br.upe.booklubapi.domain.core.repositories.CrudRepository;

public interface BookUserProgressRepository extends CrudRepository<BookUserProgress, UUID> {
}
