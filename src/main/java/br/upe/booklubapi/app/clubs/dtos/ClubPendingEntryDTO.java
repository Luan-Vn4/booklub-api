package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import java.util.UUID;

public record ClubPendingEntryDTO(
   UUID userId,
   UUID clubId,
   EntryType entryType
) {}
