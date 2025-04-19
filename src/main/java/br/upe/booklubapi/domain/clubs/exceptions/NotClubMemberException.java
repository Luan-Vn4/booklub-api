package br.upe.booklubapi.domain.clubs.exceptions;

import lombok.experimental.StandardException;

import java.util.UUID;

@StandardException
public class NotClubMemberException extends RuntimeException {

    public NotClubMemberException(UUID userId, UUID clubId) {
        this((
           "User with id \"%s\" is not a member of the club with id \"%s\"."
        ).formatted(userId, clubId));
    }

}
