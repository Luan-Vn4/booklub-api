package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.app.activities.dtos.ClubActivityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record MeetingDefinedActivityDTO(
    UUID id,
    LocalDateTime createdAt,
    UUID clubId,
    UUID meetingId
) implements ClubActivityDTO {

    @Override
    @JsonProperty("type")
    public String type() {
        return "MEETING_DEFINED";
    }

}
