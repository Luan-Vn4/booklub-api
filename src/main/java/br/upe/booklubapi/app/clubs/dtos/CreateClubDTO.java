package br.upe.booklubapi.app.clubs.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public record CreateClubDTO(
    @NotNull
    @Size(max = 50)
    String name,
    MultipartFile image,
    @NotNull
    Boolean isPrivate,
    UUID ownerId
) {}