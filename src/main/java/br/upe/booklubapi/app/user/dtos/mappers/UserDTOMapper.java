package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface UserDTOMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
