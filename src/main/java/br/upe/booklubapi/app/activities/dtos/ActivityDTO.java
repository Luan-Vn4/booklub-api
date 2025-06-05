package br.upe.booklubapi.app.activities.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ActivityDTO {

    UUID id();

    LocalDateTime createdAt();

}
