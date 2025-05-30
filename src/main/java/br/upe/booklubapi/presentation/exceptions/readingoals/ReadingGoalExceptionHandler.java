package br.upe.booklubapi.presentation.exceptions.readingoals;

import br.upe.booklubapi.domain.clubs.exceptions.AlreadyClubMemberException;
import br.upe.booklubapi.domain.readinggoals.exceptions.ConflictingReadingGoalException;
import br.upe.booklubapi.domain.readinggoals.exceptions.ReadingGoalNotFoundException;
import br.upe.booklubapi.presentation.exceptions.core.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ReadingGoalExceptionHandler {

    @ExceptionHandler(ConflictingReadingGoalException.class)
    public ResponseEntity<ExceptionBody> handle(ConflictingReadingGoalException e) {
        final HttpStatus status = HttpStatus.CONFLICT;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Conflicting dates")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(ReadingGoalNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(ReadingGoalNotFoundException e) {
        final HttpStatus status = HttpStatus.NOT_FOUND;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Reading Goal Not Found")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

}
