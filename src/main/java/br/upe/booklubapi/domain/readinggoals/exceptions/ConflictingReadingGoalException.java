package br.upe.booklubapi.domain.readinggoals.exceptions;

import java.time.LocalDate;

public class ConflictingReadingGoalException extends RuntimeException {

    public ConflictingReadingGoalException(
        LocalDate startDate,
        LocalDate endDate
    ) {
        super((
            "The interval between start date \"%s\" and end date \"%s\" " +
            "conflicts with another reading goal"
        ).formatted(startDate, endDate));
    }

}
