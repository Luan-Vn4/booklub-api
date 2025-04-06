package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import org.mapstruct.*;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy= ReportingPolicy.IGNORE,
    uses={UserMediaStorageService.class},
    injectionStrategy=InjectionStrategy.CONSTRUCTOR
)
public abstract class UserDTOMapper {

    protected UserMediaStorageService userMediaStorageService;

    @Mapping(
        target="imageUrl",
        source="imageUrl",
        qualifiedByName="resolveImageUrl"
    )
    public abstract UserDTO toDto(User user);

    @Named("resolveImageUrl")
    protected String resolveImageUrl(String profilePicPath) {
        return userMediaStorageService.getProfilePictureUrl(profilePicPath);
    }

}
