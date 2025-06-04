package br.upe.booklubapi.domain.readinggoals.exceptions;

import java.time.LocalDate;

public class IllegalReadingGoalDate extends RuntimeException {

    public IllegalReadingGoalDate(LocalDate startDate, LocalDate endDate) {
        super((
            "Start date (%s) must come before end date (%s)"
        ).formatted(startDate, endDate));
    }

}
