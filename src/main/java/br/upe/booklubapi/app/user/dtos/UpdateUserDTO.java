package br.upe.booklubapi.app.user.dtos;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record UpdateUserDTO(
    @Nullable
    String username,
    @Nullable
    String email,
    @Nullable
    String firstName,
    @Nullable
    String lastName,
    @Nullable
    MultipartFile image
) {}
