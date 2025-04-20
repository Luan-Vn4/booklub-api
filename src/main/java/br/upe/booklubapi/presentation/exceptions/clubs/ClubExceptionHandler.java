package br.upe.booklubapi.presentation.exceptions.clubs;

import br.upe.booklubapi.domain.clubs.exceptions.*;
import br.upe.booklubapi.presentation.exceptions.core.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class ClubExceptionHandler {

    @ExceptionHandler(AlreadyClubMemberException.class)
    public ResponseEntity<ExceptionBody> handle(AlreadyClubMemberException e) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Already a member")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(ClubNotFoundException e) {
        final HttpStatus status = HttpStatus.NOT_FOUND;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Club not found")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(NotClubMemberException.class)
    public ResponseEntity<ExceptionBody> handle(NotClubMemberException e) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Not a member")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(UnauthorizedClubActionException.class)
    public ResponseEntity<ExceptionBody> handle(
        UnauthorizedClubActionException e
    ) {
        final HttpStatus status = HttpStatus.UNAUTHORIZED;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Unauthorized club")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

    @ExceptionHandler(ClubPendingEntryNotFoundException.class)
    public ResponseEntity<ExceptionBody> handle(
        ClubPendingEntryNotFoundException e
    ) {
        final HttpStatus status = HttpStatus.NOT_FOUND;

        final var resp = ExceptionBody.builder()
            .httpStatus(status.value())
            .error("Club pending entry not found")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(status).body(resp);
    }

}
