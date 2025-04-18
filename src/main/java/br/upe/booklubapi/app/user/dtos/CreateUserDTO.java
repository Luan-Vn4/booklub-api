package br.upe.booklubapi.app.user.dtos;

import org.springframework.web.multipart.MultipartFile;

public record CreateUserDTO(
    String username,
    String email,
    String firstName,
    String lastName,
    MultipartFile image,
    String password
) {}