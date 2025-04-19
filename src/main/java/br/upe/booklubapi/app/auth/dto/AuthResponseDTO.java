package br.upe.booklubapi.app.auth.dto;

import br.upe.booklubapi.app.auth.dto.TokenDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;

public record AuthResponseDTO(UserDTO user, TokenDTO token) {
}
