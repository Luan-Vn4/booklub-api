package br.upe.booklubapi.domain.clubs.exceptions;

import lombok.experimental.StandardException;

import java.util.UUID;

@StandardException
public class AlreadyClubMemberException extends RuntimeException {

  public AlreadyClubMemberException(UUID userId, UUID clubId) {
    this((
        "User with id \"%s\" is already a member of club with id \"%s\"."
    ).formatted(userId, clubId));
  }

}
