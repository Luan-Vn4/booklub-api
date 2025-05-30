package br.upe.booklubapi.app.readinggoals.dtos;

import br.upe.booklubapi.domain.core.exceptions.IllegalQueryException;
import br.upe.booklubapi.domain.readinggoals.QReadingGoal;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.time.LocalDate;
import java.util.Optional;

public record ReadingGoalQueryDTO(
    Optional<LocalDate> startDate,
    Optional<LocalDate> endDate
) {

    public BooleanExpression getQuery(QReadingGoal readingGoal) {
        validate();

        var startDateAfter = startDate
            .map(readingGoal.startDate::goe)
            .orElse(Expressions.TRUE);

        var endDateBefore = endDate
            .map(readingGoal.endDate::goe)
            .orElse(Expressions.TRUE);

        return startDateAfter.and(endDateBefore);
    }

    private void validate() {
        if (
            startDate.isPresent()
            && endDate.isPresent()
            && startDate.get().isAfter(endDate.get())
        ) {
            throw new IllegalQueryException(
                "Start date %s must be set before the end date %s"
                    .formatted(startDate.get(), endDate.get())
            );
        }
    }

}
