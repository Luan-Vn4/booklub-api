package br.upe.booklubapi.domain.clubs.exceptions;

import java.util.UUID;

public class ClubPendingEntryNotFoundException extends RuntimeException {

    public ClubPendingEntryNotFoundException(UUID clubId, UUID userId) {
        super((
            "No pending entry found for club with id \"%s\" " +
            "and user with id \"%s\"."
        ).formatted(clubId, userId));
    }

}
