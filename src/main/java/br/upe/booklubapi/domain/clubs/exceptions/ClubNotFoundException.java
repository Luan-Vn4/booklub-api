package br.upe.booklubapi.domain.clubs.exceptions;

import lombok.experimental.StandardException;

import java.util.UUID;

@StandardException
public class ClubNotFoundException extends RuntimeException {

    public ClubNotFoundException(UUID id) {
        super(String.format("Club with id %s not found", id));
    }

}
