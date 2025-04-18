package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UpdateUserDTOMapper {
    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="image", ignore=true)
    User partialUpdate(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}

