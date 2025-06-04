package br.upe.booklubapi.domain.readinggoals.exceptions;

import java.util.UUID;

public class ReadingGoalNotFoundException extends RuntimeException {

    public ReadingGoalNotFoundException(UUID readingGoalId) {
        super(
            "Reading goal with id " + readingGoalId + " not found"
        );
    }

}
