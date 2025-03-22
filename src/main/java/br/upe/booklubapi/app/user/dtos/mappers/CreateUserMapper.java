package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface CreateUserMapper {
    CreateUserDTO toDto(User user);

    @Mapping(target = "id", ignore=true)
    User toEntity(CreateUserDTO createUserDTO);
}
