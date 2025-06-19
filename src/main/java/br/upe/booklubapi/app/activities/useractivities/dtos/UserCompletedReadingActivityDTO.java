package br.upe.booklubapi.app.activities.useractivities.dtos;

import br.upe.booklubapi.app.activities.dtos.UserActivityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserCompletedReadingActivityDTO(
    UUID id,
    LocalDateTime createdAt,
    UUID userId,
    UUID bookId
) implements UserActivityDTO {

    @Override
    @JsonProperty("type")
    public String type() {
        return "USER_COMPLETED_READING";
    }

}
