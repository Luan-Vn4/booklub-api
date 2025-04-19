package br.upe.booklubapi.domain.clubs.exceptions;

import java.util.UUID;

public class AlreadyClubMemberException extends RuntimeException {

  public AlreadyClubMemberException(UUID userId, UUID clubId) {
    super((
        "User with id \"%s\" is already a member of club with id \"%s\"."
    ).formatted(userId, clubId));
  }

}
