package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy= ReportingPolicy.IGNORE,
    uses=UserDTOMapperHelper.class
)
public interface UserDTOMapper {
    User toEntity(UserDTO userDTO);

    @Mapping(source="user", target="imageUrl",  qualifiedByName="imageUrltoAttributes")
    UserDTO toDTO(User user);
}

@Component
@AllArgsConstructor
class UserDTOMapperHelper {
    private final UserMediaStorageService userMediaStorageService;

    @Named("imageUrltoAttributes")
    public String handleImageUrlMapping(User user) {
        if(user.getImage() == null) {
            return null;
        }
        return userMediaStorageService.getProfilePictureUrl(user.getImage());
    }
}