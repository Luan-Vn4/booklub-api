package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface UserDTOMapper {
    UserDTO toDto(User user);

    @Mapping(target="password", ignore=true)
    User toEntity(UserDTO userDTO);
}
