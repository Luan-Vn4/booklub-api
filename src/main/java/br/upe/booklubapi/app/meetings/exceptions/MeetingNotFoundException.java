package br.upe.booklubapi.app.meetings.exceptions;

import br.upe.booklubapi.domain.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MeetingNotFoundException extends HttpResponseException {

    public MeetingNotFoundException(UUID meetingId) {
        super(
            HttpStatus.NOT_FOUND,
            "Meeting Not Found",
            "Meeting with id \"%s\" not found.".formatted(meetingId.toString())
        );
    }

}
