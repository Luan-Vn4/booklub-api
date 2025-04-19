package br.upe.booklubapi.domain.clubs.exceptions;

import java.util.UUID;

public class NotClubMemberException extends RuntimeException {

    public NotClubMemberException(UUID userId, UUID clubId) {
        super((
           "User with id \"%s\" is not a member of the club with id \"%s\"."
        ).formatted(userId, clubId));
    }

}
