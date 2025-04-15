package br.upe.booklubapi.app.user.dtos;

import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.lang.Nullable;

public record CreateUserDTO(
    String username,
    String email,
    String firstName,
    String lastName,
    @Nullable
    MultipartFile image,
    String password
) {}