package br.upe.booklubapi.app.meetings.exceptions;

import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MeetingNotDefinedException extends HttpResponseException {

    public MeetingNotDefinedException(UUID readingGoalId) {
        super(
            HttpStatus.NO_CONTENT,
            "Meeting Not Defined",
            "Reading goal with id \"%s\" does not have any meeting defined yet."
                .formatted(readingGoalId)
        );
    }

}
