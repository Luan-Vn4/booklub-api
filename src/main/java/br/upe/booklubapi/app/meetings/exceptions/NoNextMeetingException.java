package br.upe.booklubapi.app.meetings.exceptions;

import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NoNextMeetingException extends HttpResponseException {

    public NoNextMeetingException(UUID clubId) {
        super(
            HttpStatus.NO_CONTENT,
            "No Next Meeting",
            "The club with id \"%s\" does not have a next meeting defined"
                .formatted(clubId)
        );
    }

}
