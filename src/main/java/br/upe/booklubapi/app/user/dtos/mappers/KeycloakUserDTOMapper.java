package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface KeycloakUserDTOMapper {
    KeycloakUserDTO toDto(KeycloakUser user);

    @Mapping(target = "password", ignore = true)
    KeycloakUser toEntity(KeycloakUserDTO dto);
}
