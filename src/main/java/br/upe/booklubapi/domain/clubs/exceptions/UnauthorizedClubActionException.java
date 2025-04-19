package br.upe.booklubapi.domain.clubs.exceptions;

import lombok.experimental.StandardException;

import java.util.UUID;

@StandardException
public class UnauthorizedClubActionException extends RuntimeException {

    public UnauthorizedClubActionException(
        String action,
        UUID unauthorizedUserId,
        UUID clubId
    ) {
        this((
            "User with id \"%s\" is not authorized to do " +
            "action \"%s\" for club with id \"%s\"."
        ).formatted(unauthorizedUserId, action, clubId));
    }

}
