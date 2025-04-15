package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy= ReportingPolicy.IGNORE
)
public interface UserDTOMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}