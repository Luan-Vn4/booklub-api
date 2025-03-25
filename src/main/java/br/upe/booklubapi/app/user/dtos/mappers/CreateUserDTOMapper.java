package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(componentModel=MappingConstants.ComponentModel.SPRING)
public interface CreateUserDTOMapper {
    CreateUserDTO toDto(User user);

    @Mapping(target = "id", ignore=true)
    User toEntity(CreateUserDTO createUserDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore=true)
    User partialUpdate(CreateUserDTO createUserDTO, @MappingTarget User user);
}
