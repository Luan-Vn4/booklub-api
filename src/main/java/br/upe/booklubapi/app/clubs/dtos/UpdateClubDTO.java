package br.upe.booklubapi.app.clubs.dtos;

import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record UpdateClubDTO(
    @Size(max = 50)
    String name,
    MultipartFile image,
    Boolean isPrivate
) implements Serializable {}