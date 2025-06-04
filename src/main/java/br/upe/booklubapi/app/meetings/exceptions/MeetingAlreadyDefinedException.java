package br.upe.booklubapi.app.meetings.exceptions;

import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MeetingAlreadyDefinedException extends HttpResponseException {

    public MeetingAlreadyDefinedException(UUID readingGoalId) {
        super(
            HttpStatus.CONFLICT,
            "Meeting Already Defined",
            "The meeting for the reading goal with id \"%s\" is already defined."
        );
    }

}
