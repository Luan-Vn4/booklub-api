package br.upe.booklubapi.domain.clubs.exceptions;

import java.util.UUID;

public class ClubOwnerRemovalException extends RuntimeException {

    public ClubOwnerRemovalException(UUID ownerId, UUID clubId) {
        super((
            "Owner with id \"%s\" cannot be removed from their own " +
            "club with id \"%s\"."
        ).formatted(ownerId, clubId));
    }

}
