package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.app.activities.dtos.ClubActivityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberCompletedReadingActivityDTO(
    UUID id,
    LocalDateTime createdAt,
    UUID clubId,
    UUID userId,
    String bookId
) implements ClubActivityDTO {

    @Override
    @JsonProperty("type")
    public String type() {
        return "MEMBER_COMPLETED_READING";
    }

}
