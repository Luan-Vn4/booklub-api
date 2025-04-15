package br.upe.booklubapi.app.user.dtos;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.upe.booklubapi.utils.AttributeMapDeserializer;

import java.util.List;

public record UserDTO(
        String id,
        String username,
        String email,
        String firstName,
        String lastName,
        @JsonDeserialize(using = AttributeMapDeserializer.class)
        Map<String, String> attributes) {
}
