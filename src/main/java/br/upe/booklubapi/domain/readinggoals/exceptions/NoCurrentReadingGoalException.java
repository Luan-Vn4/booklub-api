package br.upe.booklubapi.domain.readinggoals.exceptions;

import java.util.UUID;

public class NoCurrentReadingGoalException extends RuntimeException {

    public NoCurrentReadingGoalException(UUID clubId) {
        super((
            "Club with id %s does not have any reading goal defined currently."
        ).formatted(clubId));
    }

}
