package br.upe.booklubapi.infra.books.repositories;

import br.upe.booklubapi.domain.books.entities.BookUserProgress;
import br.upe.booklubapi.domain.books.repositories.BookUserProgressRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookUserProgressRepository extends JpaRepository<BookUserProgress, UUID>, BookUserProgressRepository {
}
