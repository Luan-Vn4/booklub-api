package br.upe.booklubapi.domain.readinggoals.repositories;

import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserReadingGoalRepository {

    Page<ReadingGoal> findUserReadingGoals(
        UUID userId,
        Predicate predicate,
        Pageable pageable
    );

}
