package br.upe.booklubapi.app.meetings.dtos;

import br.upe.booklubapi.utils.models.SimpleCoordinate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

public record UpdateMeetingDTO(
    @Size(max = 255)
    @NotEmpty
    @NotEmpty
    String address,
    @NotNull
    SimpleCoordinate latlng
) implements Serializable {}
